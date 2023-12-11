package parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Stack;

import Log.Log;
import scanner.token.Token;

public class Parser {
    private ArrayList<Rule> rules;
    private Stack<Integer> parsStack;
    private ParseTable parseTable;

    public Parser() {
        parsStack = new Stack<Integer>();
        parsStack.push(0);
        try {
            parseTable = new ParseTable(Files.readAllLines(Paths.get("src/main/resources/parseTable")).get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
        rules = new ArrayList<Rule>();
        try {
            for (String stringRule : Files.readAllLines(Paths.get("src/main/resources/Rules"))) {
                rules.add(new Rule(stringRule));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Action getCurrentAction(Token lookAhead) {
        Action currentAction;
        Log.print(lookAhead.toString() + "\t" + parsStack.peek());
        currentAction = parseTable.getActionTable(parsStack.peek(), lookAhead);
        return currentAction;
    }
    public void PushParsStack(Action currentAction) {
        parsStack.push(currentAction.number);
    }
    public Rule getRule(Action currentAction) {
        return rules.get(currentAction.number);
    }
    public void UpdateParsStack(Rule rule) {
        for (int i = 0; i < rule.RHS.size(); i++) {
            parsStack.pop();
        }
        Log.print(parsStack.peek() + "\t" + rule.LHS);
        parsStack.push(parseTable.getGotoTable(parsStack.peek(), rule.LHS));
        Log.print(parsStack.peek() + "");
    }
}
