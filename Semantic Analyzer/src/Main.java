import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.antlr.runtime.tree.ParseTree;
import org.antlr.v4.gui.Trees;
import org.antlr.v4.runtime.*;

public class Main {
    public static void main(String[] args) throws Exception {
        String types_mismatch = "test_cases/types_mismatch.cl";
        String invalid_inheritance = "test_cases/invalid_inheritance.cl";
        String forbidden_redefinitions = "test_cases/forbidden_redefinitions.cl";
        String cyclic_inheritance ="test_cases/cyclic_inheritance.cl";
        String helloworld ="test_cases/helloworld.cl";
        String inputFile = types_mismatch;

        try (
                FileWriter file = new FileWriter(inputFile + "-pt");
                BufferedWriter writer = new BufferedWriter(file);
                FileInputStream inputStream = new FileInputStream(inputFile)
        ){
            CharStream charStream = CharStreams.fromStream(inputStream);
            LexicalAnalyzer lexer = new LexicalAnalyzer(charStream);
//          ParserLexer lexer = new ParserLexer(charStream);

            CommonTokenStream tokens = new CommonTokenStream(lexer);

            tokens.fill(); // Ensure tokens are loaded
            System.out.println("Total tokens: " + tokens.size());

            for (Token token : tokens.getTokens()) {
                System.out.println("'" + token.getText() + "' -> " + lexer.getVocabulary().getSymbolicName(token.getType()));
            }

            ParserParser parser = new ParserParser(tokens);

            parser.removeErrorListeners();
            parser.addErrorListener(new BaseErrorListener() {
                @Override
                public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                                        int line, int charPositionInLine, String msg, RecognitionException e) {
                    System.err.printf("Syntax error at line %d:%d - %s%n", line, charPositionInLine, msg);
                }
            });

            parser.setBuildParseTree(true);
            ParserParser.ProgContext tree = parser.prog(); // Entry point
            writer.write(tree.toStringTree(parser));
            writer.close();
            System.out.println(tree.toStringTree(parser));
//            Trees.inspect(tree, parser);

            AstBuilder builder = new AstBuilder();
            AST.program ast = (AST.program) builder.visit(tree);
            Semantic Semantic_Analyzer = new Semantic(ast);
        }
    }
}