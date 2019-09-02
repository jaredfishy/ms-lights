package za.co.jaredfishy.mslights.application.domain.light;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Light {

    private String id;
    private String model;
    private String firmwareVersion;
    private String support;
    private boolean powered;
    private int bright;
    private String colorMode;
    private int ct;
    private long rgb;
    private int hue;
    private int sat;
    private String name;

    private LightLocation location;

    public Light(
            String id,
            String model,
            String firmwareVersion,
            String support,
            boolean powered,
            int bright,
            String colorMode,
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
    public String getModel() {
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
