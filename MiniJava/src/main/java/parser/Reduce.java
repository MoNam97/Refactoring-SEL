package parser;

public class Reduce extends State {
    public Reduce() {
        super(act.reduce);
    }

    @Override
    public String GetString(int number) {
        return "r" + number;
    }
}
