package parser;

public class Accept extends State {

    public Accept() {
        super(act.accept);
    }

    @Override
    public String GetString(int number) {
        return "acc";
    }
}
