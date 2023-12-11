package parser;

public abstract class State {
    public final act action;
    public State(act action) {
        this.action = action;
    }

    public abstract String GetString(int number);
}
