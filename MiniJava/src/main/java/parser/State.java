package parser;

public abstract class State {
    public final act action;
    protected State(act action) {
        this.action = action;
    }

    public abstract String GetString(int number);
}
