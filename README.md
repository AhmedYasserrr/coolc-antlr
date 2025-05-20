### **coolc-antlr**  
*A Cool programming language compiler using ANTLR.*  

## 📌 **Overview**  
`coolc-antlr` is a compiler for the **Cool programming language**, built using **ANTLR** for lexical analysis and parsing, and extended with semantic analysis and intermediate code generation. The project follows a modular, multi-stage compiler pipeline:

1. **Lexical Analyzer** – Tokenizes the input source code.  
2. **Parser** – Constructs the parse tree and validates syntax.  
3. **AST Builder** – Converts the parse tree into a compact and type-safe abstract syntax tree (AST).  
4. **Semantic Analyzer** – Validates types, scope rules, inheritance, and conformance.  
5. **Intermediate Code Generator** – Emits Three-Address Code (TAC) for program execution.

---
##  **Usage**  

### **1️⃣ Running the Lexical Analyzer**  
The lexer processes a `.cl` source file and outputs a list of **tokens** in `{input_file}-lex`.

### **2️⃣ Running the Parser**  
The parser constructs a **parse tree** and then invokes the **AST builder** to produce an **abstract syntax tree**, saved as `{input_file}-ast`.

### **3️⃣ Running Semantic Analysis**  
Semantic analysis is performed on the AST:
- Reports errors related to scoping, type mismatches, invalid inheritance, method dispatch, and more.
- Outputs success if the program is semantically valid.

### **4️⃣ Generating Intermediate Code (TAC)**  
Upon passing semantic checks, the compiler emits **Three-Address Code (TAC)** into `{input_file}-tac`. This intermediate code is:
- Class-structured with vtables and constructors
- Includes runtime methods and system initialization
- Ready for interpretation or backend compilation

---

## 📜 **License**  
This project is open-source and licensed under the [MIT License](LICENSE).  
