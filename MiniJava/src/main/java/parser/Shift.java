package parser;
public class Shift extends State {

    public Shift() {
        super(act.shift);
    }

    @Override
    public String GetString(int number) {
        return "s" + number;
    }
}