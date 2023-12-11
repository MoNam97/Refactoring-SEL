package parser;

public class Action {
    public State actionState;
    //if action = shift : number is state
    //if action = reduce : number is number of rule
    public int number;

    public Action(State actionState, int number) {
        this.actionState = actionState;
        this.number = number;
    }

    public String toString() {
        return actionState.GetString(number);
    }
}

enum act {
    shift, reduce, accept
}
