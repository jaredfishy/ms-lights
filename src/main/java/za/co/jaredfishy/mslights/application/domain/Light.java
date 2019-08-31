package za.co.jaredfishy.mslights.application.domain;

import za.co.jaredfishy.mslights.application.domain.command.CommandMethod;
import za.co.jaredfishy.mslights.application.domain.status.ColorMode;
import za.co.jaredfishy.mslights.application.domain.status.LightModel;

import java.util.List;

public class Light {

    private String id;
    private LightModel model;
    private String firmwareVersion;
    private List<CommandMethod> support;
    private boolean power;
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
            boolean power,
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
        this.power = power;
        this.bright = bright;
        this.colorMode = colorMode;
        this.ct = ct;
        this.rgb = rgb;
        this.hue = hue;
        this.sat = sat;
        this.name = name;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public LightLocation getLocation() {
        return location;
    }
}
