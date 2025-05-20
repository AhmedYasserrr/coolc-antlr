import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.*;
/**  Parse‑tree → AST builder. */
public final class AstBuilder extends ParserBaseVisitor<Object> {

    /**
     * Class name currently being parsed (needed for 'self').
     */
    private String currentClass = null;

    /* ───────────────────────────────────────────────────────────── */
    /* Helper casts so you don’t sprinkle `(expression)` everywhere */
    /* ───────────────────────────────────────────────────────────── */

    private static AST.expression e(Object n) {
        return (AST.expression) n;
    }

    @SuppressWarnings("unchecked")
    private static <T> List<T> list(Object n) {
        return (List<T>) n;
    }

    @Override
    public AST.program visitStart(ParserParser.StartContext ctx) {
        List<AST.class_> classes = new ArrayList<>();
        for (ParserParser.ClassDefineContext cctx : ctx.classDefine())
            classes.add((AST.class_) visit(cctx));
        return new AST.program(classes, ctx.start.getLine());
    }

    @Override
    public AST.class_ visitClassdef(ParserParser.ClassdefContext ctx) {
        currentClass = ctx.TYPE_IDENTIFIER(0).getText();
        String name = currentClass;
        String parent = ctx.TYPE_IDENTIFIER().size() == 2
                ? ctx.TYPE_IDENTIFIER(1).getText()
                : "Object";
        String filename = ctx.start.getTokenSource().getSourceName();

        List<AST.feature> feats = list(visit(ctx.classBody()));
        return new AST.class_(name, filename, parent, feats, ctx.start.getLine());
    }

    @Override
//    @SuppressWarnings("unchecked")
    public List<AST.feature> visitClassBody(ParserParser.ClassBodyContext ctx) {
        List<AST.feature> f = new ArrayList<>();
        for (var child : ctx.children)         // attr | method
            f.add((AST.feature) visit(child));
        return f;
    }

    @Override
    public AST.attr visitAttr(ParserParser.AttrContext ctx) {
        String name = ctx.OBJECT_IDENTIFIER().getText();
        String type = ctx.TYPE_IDENTIFIER().getText();
        AST.expression init_expr = ctx.expr() != null
                ? e(visit(ctx.expr()))
                : new AST.no_expr(ctx.start.getLine());
        return new AST.attr(name, type, init_expr, ctx.start.getLine());
    }

    @Override
    public AST.method visitMethod(ParserParser.MethodContext ctx) {
        String name = ctx.OBJECT_IDENTIFIER().getText();
        String retTyp = ctx.TYPE_IDENTIFIER().getText();

        List<AST.formal> formals = new ArrayList<>();
        if (ctx.parm() != null)
            for (var pctx : ctx.parm())
                formals.add((AST.formal) visit(pctx));

        AST.expression body = e(visit(ctx.expr()));
        return new AST.method(name, formals, retTyp, body, ctx.start.getLine());
    }

    @Override
    public AST.formal visitParm(ParserParser.ParmContext ctx) {
        return new AST.formal(ctx.OBJECT_IDENTIFIER().getText(),
                ctx.TYPE_IDENTIFIER().getText(),
                ctx.start.getLine());
    }

    @Override
    public AST.plus visitAdd(ParserParser.AddContext ctx) {
        AST.expression left = e(visit(ctx.expr(0)));
        AST.expression right = e(visit(ctx.expr(1)));
        return new AST.plus(left, right, ctx.start.getLine());
    }

    @Override
    public AST.mul visitMul(ParserParser.MulContext ctx) {
        AST.expression left = e(visit(ctx.expr(0)));
        AST.expression right = e(visit(ctx.expr(1)));
        return new AST.mul(left, right, ctx.start.getLine());
    }

    @Override
    public AST.sub visitSub(ParserParser.SubContext ctx) {
        AST.expression left = e(visit(ctx.expr(0)));
        AST.expression right = e(visit(ctx.expr(1)));
        return new AST.sub(left, right, ctx.start.getLine());
    }

    @Override
    public AST.divide visitDiv(ParserParser.DivContext ctx) {
        AST.expression left = e(visit(ctx.expr(0)));
        AST.expression right = e(visit(ctx.expr(1)));
        return new AST.divide(left, right, ctx.start.getLine());
    }

    @Override
    public AST.lt visitLt(ParserParser.LtContext ctx) {
        AST.expression left = e(visit(ctx.expr(0)));
        AST.expression right = e(visit(ctx.expr(1)));
        return new AST.lt(left, right, ctx.start.getLine());
    }

    @Override
    public AST.leq visitLteq(ParserParser.LteqContext ctx) {
        AST.expression left = e(visit(ctx.expr(0)));
        AST.expression right = e(visit(ctx.expr(1)));
        return new AST.leq(left, right, ctx.start.getLine());
    }

    @Override
    public AST.eq visitEq(ParserParser.EqContext ctx) {
        AST.expression left = e(visit(ctx.expr(0)));
        AST.expression right = e(visit(ctx.expr(1)));
        return new AST.eq(left, right, ctx.start.getLine());
    }

    @Override
    public AST.isvoid visitVoid(ParserParser.VoidContext ctx) {
        return new AST.isvoid(e(visit(ctx.expr())), ctx.start.getLine());
    }

    @Override
    public AST.comp visitInvert(ParserParser.InvertContext ctx) {
        return new AST.comp(e(visit(ctx.expr())), ctx.start.getLine());
    }

    @Override
    public AST.neg visitNot(ParserParser.NotContext ctx) {
        return new AST.neg(e(visit(ctx.expr())), ctx.start.getLine());
    }

    @Override
    public AST.int_const visitNum(ParserParser.NumContext ctx) {
        return new AST.int_const(Integer.parseInt(ctx.INT().getText()),
                ctx.start.getLine());
    }

    @Override
    public AST.string_const visitString(ParserParser.StringContext ctx) {
        String raw = ctx.STRING().getText();
        return new AST.string_const(raw.substring(1, raw.length() - 1), // strip quotes
                ctx.start.getLine());
    }

    @Override
    public AST.bool_const visitBool(ParserParser.BoolContext ctx) {
        return new AST.bool_const(ctx.TRUE() != null, ctx.start.getLine());
    }

    @Override
    public AST.object visitObject(ParserParser.ObjectContext ctx) {
        return new AST.object(ctx.OBJECT_IDENTIFIER().getText(),
                ctx.start.getLine());
    }

    @Override
    public AST.expression visitParexpr(ParserParser.ParexprContext ctx) {
        return e(visit(ctx.expr()));
    }

    @Override
    public AST.new_ visitNewobject(ParserParser.NewobjectContext ctx) {
        return new AST.new_(ctx.TYPE_IDENTIFIER().getText(),
                ctx.start.getLine());
    }

    @Override
    public AST.assign visitAssign(ParserParser.AssignContext ctx) {
        return new AST.assign(ctx.OBJECT_IDENTIFIER().getText(),
                e(visit(ctx.expr())),
                ctx.start.getLine());
    }

    @Override
    public AST.cond visitIf(ParserParser.IfContext ctx) {
        return new AST.cond(
                e(visit(ctx.expr(0))),
                e(visit(ctx.expr(1))),
                e(visit(ctx.expr(2))),
                ctx.start.getLine());
    }

    @Override
    public AST.loop visitWhile(ParserParser.WhileContext ctx) {
        return new AST.loop(
                e(visit(ctx.expr(0))),
                e(visit(ctx.expr(1))),
                ctx.start.getLine());
    }

    @Override
    public AST.block visitBlock(ParserParser.BlockContext ctx) {
        List<AST.expression> exprs = new ArrayList<>();
        for (var ectx : ctx.expr())
            exprs.add(e(visit(ectx)));
        return new AST.block(exprs, ctx.start.getLine());
    }

    @Override
    public AST.dispatch visitShortcall(ParserParser.ShortcallContext ctx) {
        List<AST.expression> actuals = new ArrayList<>();
        if (ctx.methodCall().expr() != null)
            for (var ectx : ctx.methodCall().expr())
                actuals.add(e(visit(ectx)));

        return new AST.dispatch(
                new AST.object("self", ctx.start.getLine()),  // implicit caller
                ctx.methodCall().OBJECT_IDENTIFIER().getText(),
                actuals,
                ctx.start.getLine());
    }

    @Override
    public AST.expression visitLongcall(ParserParser.LongcallContext ctx) {
        AST.expression caller = e(visit(ctx.expr()));
        String name = ctx.methodCall().OBJECT_IDENTIFIER().getText();

        List<AST.expression> actuals = new ArrayList<>();
        if (ctx.methodCall().expr() != null)
            for (var ectx : ctx.methodCall().expr())
                actuals.add(e(visit(ectx)));

        if (ctx.TYPE_IDENTIFIER() == null) {
            return new AST.dispatch(caller, name, actuals, ctx.start.getLine());
        }
        return new AST.static_dispatch(
                caller,
                ctx.TYPE_IDENTIFIER().getText(),
                name, actuals,
                ctx.start.getLine());
    }

    @Override
    public AST.typcase visitCase(ParserParser.CaseContext ctx) {
        AST.expression predicate = e(visit(ctx.expr(0)));

        List<AST.branch> branches = new ArrayList<>();
        int n = ctx.OBJECT_IDENTIFIER().size();
        for (int i = 0; i < n; ++i) {
            String name = ctx.OBJECT_IDENTIFIER(i).getText();
            String type = ctx.TYPE_IDENTIFIER(i).getText();
            AST.expression val = e(visit(ctx.expr(i + 1)));   // first expr is predicate
            branches.add(new AST.branch(name, type, val, ctx.start.getLine()));
        }
        return new AST.typcase(predicate, branches, ctx.start.getLine());
    }
    @Override
    public AST.expression visitLet(ParserParser.LetContext ctx) {
        List<AST.binding> bindings = new ArrayList<>();

        // First binding
        String name0 = ctx.OBJECT_IDENTIFIER(0).getText();
        String type0 = ctx.TYPE_IDENTIFIER(0).getText();
        AST.expression value0 = ctx.expr(0) != null ? e(visit(ctx.expr(0))) : new AST.no_expr(ctx.start.getLine());
        bindings.add(new AST.binding(name0, type0, value0, ctx.OBJECT_IDENTIFIER(0).getSymbol().getLine()));

        // Remaining bindings (from comma-separated list)
        int exprIndex = ctx.expr(0) != null ? 1 : 0;
        int offset = 1; // offset in OBJECT_IDENTIFIER and TYPE_IDENTIFIER

        for (int i = 1; i < ctx.OBJECT_IDENTIFIER().size(); i++) {
            String name = ctx.OBJECT_IDENTIFIER(i).getText();
            String type = ctx.TYPE_IDENTIFIER(i).getText();

            AST.expression val = null;
            if (exprIndex < ctx.expr().size() - 1) {
                val = e(visit(ctx.expr(exprIndex++)));
            } else {
                val = new AST.no_expr(ctx.OBJECT_IDENTIFIER(i).getSymbol().getLine());
            }

            bindings.add(new AST.binding(name, type, val, ctx.OBJECT_IDENTIFIER(i).getSymbol().getLine()));
        }

        // The final expression after 'in'
        AST.expression body = e(visit(ctx.expr(ctx.expr().size() - 1)));

        return new AST.let("", "", bindings, body, ctx.start.getLine());
    }

}