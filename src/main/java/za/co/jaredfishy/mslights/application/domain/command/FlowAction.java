package za.co.jaredfishy.mslights.application.domain.command;

public enum FlowAction {

    RECOVER(0),
    STAY(1),
    OFF(2);

    private int action;

    FlowAction(int action) {
        this.action = action;
    }

    public int getAction() {
        return action;
    }
}
