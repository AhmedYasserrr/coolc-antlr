import java.util.*;

// Generates Three-Address Code (TAC) from the AST for COOL object-oriented language.
public class TacGen {
    private AST.program program;
    private List<String> tacInstructions = new ArrayList<>();
    private int tempCount = 0, labelCount = 0;
    private final ScopeTable<String> env = new ScopeTable<>();
    private String currentCls = null;
    private Map<String, Map<String, Integer>> classLayouts = new HashMap<>();
    private Map<String, List<String>> classMethods = new HashMap<>();
    private Map<String, String> classParents = new HashMap<>();

    public TacGen(AST.program program) {
        this.program = program;
        buildClassInfo();
    }
    private void emit(String s) { tacInstructions.add(s); }
    private String newTemp()    { return "t" + (++tempCount); }
    private String newLabel()   { return "L" + (++labelCount); }
    private void bind(String id, String place) { env.insert(id, place); }
    private String lookup(String id) {
        String p = env.lookUpGlobal(id);
        if (p == null) throw new RuntimeException("Unbound id \"" + id + "\"");
        return p;
    }
    private void blank()          { emit(""); }
    private List<String> formatMethodNames(String className, List<String> methods) {
        List<String> qualified = new ArrayList<>();
        for (String m : methods) {
            qualified.add(className + "_" + m);
        }
        return qualified;
    }


    private void generateBuiltInClasses() {
        /* ---------------- Object ----------------------------------------------- */
        emit("# ----- Built-in Class: Object -----");
        emit("class Object {");
        emit("  vtable Object = [ Object_abort , Object_type_name ]");
        emit("}");

        emit("Object_abort:");
        emit("  t1 = const \"Abort called\\n\"");
        emit("  param t1");
        emit("  call _print_string");
        emit("  call _halt");
        emit("  return 0");
        blank();

        emit("Object_type_name:");
        emit("  t1 = this.type_name");
        emit("  return t1");
        blank();

        emit("Object_init:");
        emit("  param obj");
        emit("  return obj");
        blank();

        /* ---------------- IO --------------------------------------------------- */
        emit("# ----- Built-in Class: IO -----");
        emit("class IO inherits Object {");
        emit("  vtable IO = [ Object.vtable, IO_out_string , IO_out_int , IO_in_string , IO_in_int ]");
        emit("}");

        emit("IO_out_string:");
        emit("  param x");
        emit("  call _print_string , x");
        emit("  return this");
        blank();

        emit("IO_out_int:");
        emit("  param x");
        emit("  call _print_int , x");
        emit("  return this");
        blank();

        emit("IO_in_string:");
        emit("  t1 = call _read_string");
        emit("  return t1");
        blank();

        emit("IO_in_int:");
        emit("  t1 = call _read_int");
        emit("  return t1");
        blank();

        /* ---------------- String ------------------------------------------------ */
        emit("# ----- Built-in Class: String -----");
        emit("class String inherits Object {");
        emit("  vtable String = [Object.vtable , String_length , String_concat , String_substr ]");
        emit("}");

        emit("String_length:");
        emit("  t1 = call _string_length , this");
        emit("  return t1");
        blank();

        emit("String_concat:");
        emit("  param s");
        emit("  t1 = call _string_concat , this , s");
        emit("  return t1");
        blank();

        emit("String_substr:");
        emit("  param i");
        emit("  param l");
        emit("  t1 = call _string_substr , this , i , l");
        emit("  return t1");
        blank();

        /* ---------------- Int and Bool ------------------------------------------ */
        emit("# ----- Built-in Class: Int -----");
        emit("class Int inherits Object {");
        emit("  vtable Int = [ Object.vtable ]");
        emit("}");
        blank();

        emit("# ----- Built-in Class: Bool -----");
        emit("class Bool inherits Object {");
        emit("  vtable Bool = [ Object.vtable ]");
        emit("}");
        blank();

        /* ---------------- External Runtime Helpers ------------------------------ */
        emit("# Extern Runtime Helpers");
        emit("extern _print_string , _print_int , _read_string , _read_int");
        emit("extern _string_length , _string_concat , _string_substr");
        emit("extern _halt");
        blank();
    }
    private void generateRuntimeInit() {
        emit("# Runtime initialization");
        emit("func __init:");
        emit("call __setup_classes");
        emit("return 0");
        emit("");
    }

    private void generateMain() {
        emit("# Main entry point");
        emit("func main:");
        emit("call __init");

        // Find Main class and call main method
        emit("t_main_obj = new Main");
        emit("call Main_main, t_main_obj");
        emit("return 0");
        emit("");
    }

    // Generate TAC for the whole program
    public List<String> generate() {
        generateBuiltInClasses();
        generateRuntimeInit();

        // Generate user class definitions
        for (AST.class_ cls : program.classes) {
            generateClass(cls);
        }

        // Generate main entry point
        generateMain();

        return tacInstructions;
    }

    /* ───────── class / attribute / method ───────────────── */
    private void generateClass(AST.class_ cls) {
        currentCls = cls.name;

        emit("# Class " + cls.name);

        // Emit class and vtable
        emit("class " + cls.name + (cls.parent != null ? " inherits " + cls.parent : "") + " {");

        List<String> methods = classMethods.get(cls.name);
        String methodList = String.join(" , ", formatMethodNames(cls.name, methods));
        emit("  vtable " + cls.name + " = [ " + cls.parent +".vtable" + ", "+ methodList + " ]");

        emit("}");

        blank();

        // Generate class constructor
        generateConstructor(cls);

        // Generate methods
        for (AST.feature f : cls.features) {
            if (f instanceof AST.method) {
                generateMethod((AST.method) f);
            }
        }

        currentCls = null;
    }


    private void generateConstructor(AST.class_ cls) {
        emit("func " + cls.name + "_new:");
        String obj = newTemp();
        emit(obj + " = allocate " + getClassSize(cls.name));
        emit(obj + ".class_tag = " + cls.name);

        // Initialize parent if exists
        if (cls.parent != null) {
            emit("call " + cls.parent + "_init, " + obj);
        }

        // Initialize attributes
        env.enterScope();
        bind("self", obj);

        for (AST.feature f : cls.features) {
            if (f instanceof AST.attr) {
                generateAttrInit((AST.attr) f, obj);
            }
        }

        emit("return " + obj);
        env.exitScope();
        emit("");
    }

    private void generateAttrInit(AST.attr attr, String obj) {
        Integer offset = classLayouts.get(currentCls).get(attr.name);
        if (offset == null) return;

        String value;
        if (attr.value instanceof AST.no_expr) {
            // Use default values based on type
            value = getDefaultValue(attr.typeid);
        } else {
            value = generateExpr(attr.value);
        }
        emit(obj + "[" + offset + "] = " + value);
    }

    private String getDefaultValue(String type) {
        switch (type) {
            case "Int": return "0";
            case "Bool": return "0";
            case "String": return "\"\"";
            default: return "0"; // null for objects
        }
    }

    private int getClassSize(String className) {
        return Math.max(1, classLayouts.get(className).size());
    }

    private void generateMethod(AST.method m) {
        env.enterScope();
        emit("func " + currentCls + "_" + m.name + ":");

        // Method receives self as first parameter
        String self = newTemp();
        bind("self", self);
        emit("# self = param 0");

        // Bind formal parameters
        for (int i = 0; i < m.formals.size(); i++) {
            AST.formal f = m.formals.get(i);
            String param = newTemp();
            bind(f.name, param);
            emit("# " + f.name + " = param " + (i + 1));
        }

        String result = generateExpr(m.body);
        emit("return " + result);
        env.exitScope();
        emit("");
    }

    /* ───────── expressions ────────────────────── */

    private String generateExpr(AST.expression e) {
        if (e instanceof AST.no_expr)      return "0";
        if (e instanceof AST.int_const)    return intConst((AST.int_const) e);
        if (e instanceof AST.bool_const)   return boolConst((AST.bool_const) e);
        if (e instanceof AST.string_const) return strConst((AST.string_const) e);
        if (e instanceof AST.object)       return object((AST.object) e);

        if (e instanceof AST.neg)          return unaryOp("-", ((AST.neg) e).e1);
        if (e instanceof AST.comp)         return unaryOp("!", ((AST.comp) e).e1);
        if (e instanceof AST.isvoid)       return unaryOp("isvoid", ((AST.isvoid) e).e1);

        if (e instanceof AST.plus)         return binOp("+", ((AST.plus) e).e1, ((AST.plus) e).e2);
        if (e instanceof AST.sub)          return binOp("-", ((AST.sub) e).e1, ((AST.sub) e).e2);
        if (e instanceof AST.mul)          return binOp("*", ((AST.mul) e).e1, ((AST.mul) e).e2);
        if (e instanceof AST.divide)       return binOp("/", ((AST.divide) e).e1, ((AST.divide) e).e2);
        if (e instanceof AST.lt)           return binOp("<", ((AST.lt) e).e1, ((AST.lt) e).e2);
        if (e instanceof AST.leq)          return binOp("<=", ((AST.leq) e).e1, ((AST.leq) e).e2);
        if (e instanceof AST.eq)           return binOp("==", ((AST.eq) e).e1, ((AST.eq) e).e2);

        if (e instanceof AST.assign)       return assign((AST.assign) e);
        if (e instanceof AST.block)        return block((AST.block) e);
        if (e instanceof AST.cond)         return cond((AST.cond) e);
        if (e instanceof AST.loop)         return loop((AST.loop) e);
        if (e instanceof AST.let)          return let((AST.let) e);
        if (e instanceof AST.dispatch)     return dispatch((AST.dispatch) e);
        if (e instanceof AST.static_dispatch) return staticDispatch((AST.static_dispatch) e);
        if (e instanceof AST.new_)         return newObject((AST.new_) e);

        throw new UnsupportedOperationException(
            "TAC generation for " + e.getClass().getSimpleName());
    }

    /* ───────── individual helpers ───────────────────────── */

    private String intConst(AST.int_const n) {
        String t = newTemp();
        emit(t + " = const " + n.value);
        return t;
    }

    private String boolConst(AST.bool_const b) {
        String t = newTemp();
        emit(t + " = const " + (b.value ? 1 : 0));
        return t;
    }

    private String strConst(AST.string_const s) {
        String t = newTemp();
        emit(t + " = const \"" + s.value.replace("\"", "\\\"") + "\"");
        return t;
    }

    private String object(AST.object obj) {
        String id = obj.name;

        // Check if it's a local variable or formal parameter
        String local = env.lookUpGlobal(id);
        if (local != null) {
            return local;
        }

        // Must be an attribute access on self
        String self = lookup("self");
        Integer offset = classLayouts.get(currentCls).get(id);
        if (offset != null) {
            String t = newTemp();
            emit(t + " = " + self + "[" + offset + "]");
            return t;
        }

        throw new RuntimeException("Unbound identifier: " + id);
    }

    private String unaryOp(String op, AST.expression e) {
        String x = generateExpr(e);
        String t = newTemp();
        emit(t + " = " + op + " " + x);
        return t;
    }

    private String binOp(String op, AST.expression a, AST.expression b) {
        String x = generateExpr(a), y = generateExpr(b);
        String t = newTemp();
        emit(t + " = " + x + " " + op + " " + y);
        return t;
    }

    private String assign(AST.assign a) {
        String rhs = generateExpr(a.e1);
        String id = a.name;

        // Check if it's a local variable
        String local = env.lookUpGlobal(id);
        if (local != null) {
            emit(local + " = " + rhs);
            return local;
        }

        // Must be an attribute assignment
        String self = lookup("self");
        Integer offset = classLayouts.get(currentCls).get(id);
        if (offset != null) {
            emit(self + "[" + offset + "] = " + rhs);
            return rhs;
        }

        throw new RuntimeException("Cannot assign to unbound identifier: " + id);
    }

    private String block(AST.block b) {
        String last = "0";
        for (AST.expression e : b.l1)
            last = generateExpr(e);
        return last;
    }

    private String cond(AST.cond c) {
        String pred = generateExpr(c.predicate);
        String Lelse = newLabel(), Lend = newLabel(), t = newTemp();
        emit("ifFalse " + pred + " goto " + Lelse);
        String v1 = generateExpr(c.ifbody);
        emit(t + " = " + v1);
        emit("goto " + Lend);
        emit("label " + Lelse);
        String v2 = generateExpr(c.elsebody);
        emit(t + " = " + v2);
        emit("label " + Lend);
        return t;
    }
    private String let(AST.let l) {
        env.enterScope();

        for (AST.binding bind : l.bindings) {
            String place = newTemp();
            bind(bind.name, place);

            if (bind.value instanceof AST.no_expr) {
                // No initializer: use default value for the declared type
                String defaultVal = getDefaultValue(bind.type);
                emit(place + " = " + defaultVal);
            } else {
                String rhs = generateExpr(bind.value);
                emit(place + " = " + rhs);
            }
        }

        // Generate code for the let body
        String result = generateExpr(l.body);
        env.exitScope();
        return result;
    }

    private String loop(AST.loop l) {
        String Lcond = newLabel(), Lend = newLabel();
        emit("label " + Lcond);
        String p = generateExpr(l.predicate);
        emit("ifFalse " + p + " goto " + Lend);
        generateExpr(l.body);
        emit("goto " + Lcond);
        emit("label " + Lend);
        return "0";
    }

    private String dispatch(AST.dispatch d) {
        String recv = generateExpr(d.caller);

        // Generate runtime type checking and method lookup
        String methodAddr = newTemp();
        emit(methodAddr + " = vtable_lookup " + recv + ", "+d.caller.type +"_"+ d.name);

        // Generate arguments in reverse order (right-to-left)
        List<String> actuals = new ArrayList<>();
        for (AST.expression e : d.actuals) {
            actuals.add(generateExpr(e));
        }
        Collections.reverse(actuals);

        // Push arguments
        emit("param " + recv); // self as first argument
        actuals.forEach(a -> emit("param " + a));

        String t = newTemp();
        emit(t + " = call_indirect " + methodAddr);
        return t;
    }

    private String staticDispatch(AST.static_dispatch sd) {
        String recv = generateExpr(sd.caller);

        // For static dispatch, we know the exact method to call
        String methodName = sd.typeid + "_" + sd.caller.type +"_"+ sd.name;

        // Generate arguments in reverse order
        List<String> actuals = new ArrayList<>();
        for (AST.expression e : sd.actuals) {
            actuals.add(generateExpr(e));
        }
        Collections.reverse(actuals);

        // Push arguments
        actuals.forEach(a -> emit("param " + a));
        emit("param " + recv); // self as first argument

        String t = newTemp();
        emit(t + " = call " + methodName);
        return t;
    }

    private String newObject(AST.new_ n) {
        String t = newTemp();
        emit(t + " = call " + n.typeid + "_new");
        return t;
    }

    // Build class hierarchy and layout information
    private void buildClassInfo() {
        // First pass: collect basic class info
        for (AST.class_ cls : program.classes) {
            classLayouts.put(cls.name, new HashMap<>());
            classMethods.put(cls.name, new ArrayList<>());
            if (cls.parent != null) {
                classParents.put(cls.name, cls.parent);
            }
        }

        // Second pass: build layouts including inherited features
        for (AST.class_ cls : program.classes) {
            buildClassLayout(cls);
        }
    }

    private void buildClassLayout(AST.class_ cls) {
        Map<String, Integer> layout = classLayouts.get(cls.name);
        List<String> methods = classMethods.get(cls.name);
        int attrOffset = 0;

        // Add parent attributes and methods first
        if (cls.parent != null && classLayouts.containsKey(cls.parent)) {
            Map<String, Integer> parentLayout = classLayouts.get(cls.parent);
            for (Map.Entry<String, Integer> entry : parentLayout.entrySet()) {
                layout.put(entry.getKey(), entry.getValue());
                attrOffset = Math.max(attrOffset, entry.getValue() + 1);
            }
            List<String> parentMethods = classMethods.get(cls.parent);
            methods.addAll(parentMethods);
        }

        // Add this class's features
        for (AST.feature f : cls.features) {
            if (f instanceof AST.attr) {
                AST.attr attr = (AST.attr) f;
                if (!layout.containsKey(attr.name)) {
                    layout.put(attr.name, attrOffset++);
                }
            } else if (f instanceof AST.method) {
                AST.method method = (AST.method) f;
                String methodName = method.name;
                // Override or add method
                if (!methods.contains(methodName)) {
                    methods.add(methodName);
                }
            }
        }
    }
}