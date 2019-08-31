package za.co.jaredfishy.mslights.application.domain.command;

import org.json.JSONArray;

public abstract class Command {

    protected final CommandMethod method;

    public Command(CommandMethod method) {
        this.method = method;
    }

    public final CommandMethod getMethod() {
        return method;
    }

    public abstract JSONArray getParams();
}
