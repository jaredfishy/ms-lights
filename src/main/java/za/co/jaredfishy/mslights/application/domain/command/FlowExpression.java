package za.co.jaredfishy.mslights.application.domain.command;

public class FlowExpression {

    private int duration;
    private int mode;
    private int value;
    private int brightness;

    public FlowExpression(int duration, int mode, int value, int brightness) {
        this.duration = duration;
        this.mode = mode;
        this.value = value;
        this.brightness = brightness;
    }

    public String getFlowString() {
        return duration + "," + mode + "," + value + "," + brightness;
    }
}
