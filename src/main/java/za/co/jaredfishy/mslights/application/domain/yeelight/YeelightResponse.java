package za.co.jaredfishy.mslights.application.domain.yeelight;

import com.fasterxml.jackson.databind.JsonSerializable;
import za.co.jaredfishy.mslights.application.util.JSONSerializer;

import java.time.LocalDateTime;

public class YeelightResponse {

    private final String id;
    private final String location;
    private final String model;
    private final String firmwareVersion;
    private final String support;
    private final boolean powered;
    private final int bright;
    private final String colorMode;
    private final int ct;
    private final long rgb;
    private final int hue;
    private final int sat;
    private final String name;
    private final LocalDateTime timestamp;

    public YeelightResponse(
            String id,
            String location,
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
            LocalDateTime timestamp
    ) {
        this.id = id;
        this.location = location;
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
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public String getModel() {
        return model;
    }

    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    public String getSupport() {
        return support;
    }

    public boolean isPowered() {
        return powered;
    }

    public int getBright() {
        return bright;
    }

    public String getColorMode() {
        return colorMode;
    }

    public int getCt() {
        return ct;
    }

    public long getRgb() {
        return rgb;
    }

    public int getHue() {
        return hue;
    }

    public int getSat() {
        return sat;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String toString(){
        return JSONSerializer.serialize(this);
    }
}
