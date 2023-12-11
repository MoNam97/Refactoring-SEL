package parser;

import Log.Log;
import codeGenerator.CodeGenerator;
import errorHandler.ErrorHandler;
import scanner.lexicalAnalyzer;
import scanner.token.Token;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class InsideParserController {
    private ArrayList<Rule> rules;
    private ParseTable parseTable;
    private Parser parser;

    public InsideParserController(Parser parser) {
        this.parser = parser;
        SetParseTable();
        SetRules();
    }

    public void SetRules() {
        rules = new ArrayList<Rule>();
        try {
            for (String stringRule : Files.readAllLines(Paths.get("src/main/resources/Rules"))) {
                rules.add(new Rule(stringRule));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void SetParseTable() {
        try {
            parseTable = new ParseTable(Files.readAllLines(Paths.get("src/main/resources/parseTable")).get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Action getCurrentAction(Token lookAhead) {
        Action currentAction;
        Log.print(lookAhead.toString() + "\t" + parser.getPeek());
        currentAction = parseTable.getActionTable(parser.getPeek(), lookAhead);
        return currentAction;
    }

    public Rule getRule(Action currentAction) {
        return rules.get(currentAction.number);
    }
    public void UpdateParsStack(Rule rule) {
        parser.PopParser(rule.RHS.size());
        Log.print(parser.getPeek() + "\t" + rule.LHS);
        parser.PushParsStack(parseTable.getGotoTable(parser.getPeek(), rule.LHS));
        Log.print(parser.getPeek() + "");
    }

}
