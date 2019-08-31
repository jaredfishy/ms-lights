package za.co.jaredfishy.mslights.application.domain.command;

import org.json.JSONArray;

public class SetRGBCommand extends Command {

    private final int rgbValue;
    private final CommandEffect effect;
    private final int duration;

    public SetRGBCommand(int rgbValue, CommandEffect effect, int duration) {
        super(CommandMethod.SET_RGB);
        this.rgbValue = rgbValue;
        this.effect = effect;
        this.duration = duration;
    }

    @Override
    public JSONArray getParams() {
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(rgbValue);
        jsonArray.put(effect.getEffect());
        jsonArray.put(duration);
        return jsonArray;
    }
}
