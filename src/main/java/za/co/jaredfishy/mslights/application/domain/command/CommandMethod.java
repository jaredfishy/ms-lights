package za.co.jaredfishy.mslights.application.domain.command;

public enum CommandMethod {

    SET_POWER("set_power"),
    SET_RGB("set_rgb"),
    START_FLOW("start_cf"),
    STOP_FLOW("stop_cf");

    private String method;

    CommandMethod(String method) {
        this.method = method;
    }

    public static CommandMethod fromString(String supportString) {
        for (CommandMethod method : CommandMethod.values()) {
            if (method.name().equalsIgnoreCase(supportString) || method.method.equalsIgnoreCase(supportString))
                return method;
        }
        return null;
    }

    public String getMethod() {
        return method;
    }
}
