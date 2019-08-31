package za.co.jaredfishy.mslights.application.domain.command;

import org.json.JSONArray;

public class SetPowerCommand extends Command {

    private final boolean on;
    private final CommandEffect effect;
    private final int duration;

    public SetPowerCommand(boolean on, CommandEffect effect, int duration) {
        super(CommandMethod.SET_POWER);
        this.on = on;
        this.effect = effect;
        this.duration = duration;
    }

    @Override
    public JSONArray getParams() {
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(on ? "on" : "off");
        jsonArray.put(effect.getEffect());
        jsonArray.put(duration);
        return jsonArray;
    }
}
