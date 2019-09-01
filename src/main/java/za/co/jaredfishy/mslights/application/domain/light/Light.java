package za.co.jaredfishy.mslights.application.domain.light;

import com.fasterxml.jackson.annotation.JsonProperty;
import za.co.jaredfishy.mslights.application.domain.command.CommandMethod;
import za.co.jaredfishy.mslights.application.domain.light.status.ColorMode;
import za.co.jaredfishy.mslights.application.domain.light.status.LightModel;

import java.util.List;

public class Light {

    private String id;
    private LightModel model;
    private String firmwareVersion;
    private List<CommandMethod> support;
    private boolean powered;
    private int bright;
    private ColorMode colorMode;
    private int ct;
    private long rgb;
    private int hue;
    private int sat;
    private String name;

    private LightLocation location;

    public Light(
            String id,
            LightModel model,
            String firmwareVersion,
            List<CommandMethod> support,
            boolean powered,
            int bright,
            ColorMode colorMode,
            int ct,
            long rgb,
            int hue,
            int sat,
            String name,
            LightLocation location
    ) {
        this.id = id;
        this.model = model;
        this.firmwareVersion = firmwareVersion;
        this.support = support;
        this.powered = powered;
        this.bright = bright;
        this.colorMode = colorMode;
        this.ct = ct;
        this.rgb = rgb;
        this.hue = hue;
        this.sat = sat;
        this.name = name;
        this.location = location;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("on")
    public boolean isPowered() {
        return powered;
    }

    @JsonProperty("model")
    public LightModel getModel() {
        return model;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("location")
    public LightLocation getLocation() {
        return location;
    }
}
