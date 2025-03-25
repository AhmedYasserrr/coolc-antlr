import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.antlr.runtime.tree.ParseTree;
import org.antlr.v4.gui.Trees;
import org.antlr.v4.runtime.*;

public class Main {
    public static void main(String[] args) throws Exception {
        String badFile ="test_cases/comment_challenge.cl";
        String goodFile ="test_cases/helloworld.cl";
        String inputFile = goodFile;

        try (
            FileWriter file = new FileWriter(inputFile + "-ast");
            BufferedWriter writer = new BufferedWriter(file);
            FileInputStream inputStream = new FileInputStream(inputFile)
        ){
            CharStream charStream = CharStreams.fromStream(inputStream);
            LexicalAnalyzer lexer = new LexicalAnalyzer(charStream);
//            ParserLexer lexer = new ParserLexer(charStream);

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
            writer.write(tree.toStringTree());

            writer.close();
            System.out.println(tree.toStringTree(parser));
            Trees.inspect(tree, parser);
        }
    }
}