package za.co.jaredfishy.mslights.application.domain.status;

import za.co.jaredfishy.mslights.application.exception.UnknownLightModelException;

public enum ColorMode {

    COLOR(1), // uses RGB key
    TEMPERATURE(2), // uses CT key
    HSV(3); // uses HUE/SAT

    private int colorMode;

    ColorMode(int colorMode) {
        this.colorMode = colorMode;
    }

    public static ColorMode fromString(String colorModeString) {
        int colorMode = Integer.parseInt(colorModeString);
        for (ColorMode lightColorMode : ColorMode.values()) {
            if (lightColorMode.name().equalsIgnoreCase(colorModeString) || lightColorMode.colorMode == colorMode)
                return lightColorMode;
        }
        throw new UnknownLightModelException("Unknown color mode \"" + colorMode + "\"");
    }
}
