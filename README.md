### **coolc-antlr**  
*A Cool programming language compiler using ANTLR.*  

## 📌 **Overview**  
`coolc-antlr` is a compiler for the **Cool programming language**, built using **ANTLR** for lexical analysis and parsing. The project is structured into two main components:  

1. **Lexical Analyzer** – Tokenizes the input source code.  
2. **Parser** – Builds an abstract syntax tree (AST) and validates syntax.  

---

## 📂 **Project Structure**  
Each component (`Lexical Analyzer` and `Parser`) has the following directories:  

```
Lexical Analyzer/
│── gen/          # Lexer implementation code
│── grammar/      # Lexer grammar rules
│── src/          # Main program that runs the lexer
│── test_cases/   # Example test cases
│
Parser/
│── gen/          # Parser implementation code
│── grammar/      # Parser grammar rules
│── src/          # Main program that runs the parser
│── test_cases/   # Example test cases
```

---

## 🛠 **Usage**  

### **1️⃣ Running the Lexical Analyzer**  
The lexer processes a **Cool source file** and outputs a list of **tokens** in `{input_file}-lex`.  

### **2️⃣ Running the Parser**  
The parser **can accept either the original input file or the lexer output**. It generates an **AST (Abstract Syntax Tree)** and writes it to `{input_file}-ast`.  

---

## 📜**License**  
This project is open-source and licensed under [MIT License](LICENSE).  

