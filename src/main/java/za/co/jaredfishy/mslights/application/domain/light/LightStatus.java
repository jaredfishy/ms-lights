package za.co.jaredfishy.mslights.application.domain.light;

import za.co.jaredfishy.mslights.application.domain.FormattedDateTime;

import java.time.LocalDateTime;

public class LightStatus extends LightVariable {

    private final boolean powered;
    private final int bright;
    private final String colorMode;
    private final int ct;
    private final long rgb;
    private final int hue;
    private final int sat;

    public LightStatus(
            FormattedDateTime timestamp,
            boolean powered,
            int bright,
            String colorMode,
            int ct,
            long rgb,
            int hue,
            int sat
    ) {
        super(timestamp);
        this.powered = powered;
        this.bright = bright;
        this.colorMode = colorMode;
        this.ct = ct;
        this.rgb = rgb;
        this.hue = hue;
        this.sat = sat;
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
}
