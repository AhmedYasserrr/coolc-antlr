import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.gui.Trees;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception{
        String inputFile = "test_cases/bad_syntax.cl";
        FileWriter fileWriter = new FileWriter(inputFile + "-lex");
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        FileInputStream inputStream = new FileInputStream(inputFile);
        CharStream charStream = CharStreams.fromStream(inputStream);
        LexicalAnalyzer lexer = new LexicalAnalyzer(charStream);

        for (Token token = lexer.nextToken(); token.getType() != Token.EOF; token = lexer.nextToken()) {
            bufferedWriter.append(String.valueOf(token.getLine()));
            bufferedWriter.write(' ');
            String symbolicName = LexicalAnalyzer.VOCABULARY.getSymbolicName(token.getType());
            bufferedWriter.append(symbolicName);
            bufferedWriter.write(' ');
            String token_text = token.getText();
            bufferedWriter.append(token_text);
            bufferedWriter.newLine();

            System.out.println("Token: " + token_text + " (Type: " + symbolicName + ")");
        }

        bufferedWriter.close();
    }
}