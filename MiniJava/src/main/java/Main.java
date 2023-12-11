import parser.ParserController;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            ParserController parserController = new ParserController();
            parserController.StartParse();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
