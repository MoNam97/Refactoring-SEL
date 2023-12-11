import Log.Log;
import codeGenerator.CodeGenerator;
import errorHandler.ErrorHandler;
import parser.Action;
import parser.Parser;
import parser.Rule;
import scanner.lexicalAnalyzer;
import scanner.token.Token;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ParserController {
    private Parser parser;
    private lexicalAnalyzer lexicalAnalyzer;
    private CodeGenerator cg;
    final String address = "src/main/resources/code";
    Scanner scanner;
    public ParserController() throws FileNotFoundException {
        parser = new Parser();
        scanner = new Scanner(new File(address));
        lexicalAnalyzer = new lexicalAnalyzer(scanner);
        cg = new CodeGenerator();
    }
    public void StartParse(){
        Token lookAhead = lexicalAnalyzer.getNextToken();
        boolean finish = false;
        Action currentAction;
        while (!finish) {
            try {
                currentAction = parser.getCurrentAction(lookAhead);
                Log.print(currentAction.toString());

                switch (currentAction.getAction()) {
                    case shift:
                        lookAhead = HandleShiftState(currentAction);
                        break;

                    case reduce:
                        HandleReduce(lookAhead, currentAction);
                        break;

                    case accept:
                        finish = true;
                        break;
                }
                Log.print("");
            } catch (Exception ignored) {
                ignored.printStackTrace();
            }
        }
        if (!ErrorHandler.hasError) cg.printMemory();
    }

    public void HandleReduce(Token lookAhead, Action currentAction) {
        Rule rule = parser.getRule(currentAction);
        parser.UpdateParsStack(rule);

        try {
            cg.semanticFunction(rule.semanticAction, lookAhead);
        } catch (Exception e) {
            Log.print("Code Genetator Error");
        }
    }

    public Token HandleShiftState(Action currentAction) {
        Token lookAhead;
        parser.PushParsStack(currentAction);
        lookAhead = lexicalAnalyzer.getNextToken();
        return lookAhead;
    }

}
