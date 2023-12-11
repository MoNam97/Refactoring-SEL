package parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Stack;

import Log.Log;
import scanner.token.Token;

public class Parser {
    private Stack<Integer> parsStack;

    public Parser() {
        parsStack = new Stack<Integer>();
        parsStack.push(0);
    }

    public void PushParsStack(int number) {
        parsStack.push(number);
    }
    public void PopParser(int n) {
        for (int i = 0; i < n; i++) {
            parsStack.pop();
        }
    }
    public Integer getPeek() {
        return parsStack.peek();
    }
}
