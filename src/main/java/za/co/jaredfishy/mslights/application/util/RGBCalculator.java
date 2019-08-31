package za.co.jaredfishy.mslights.application.util;

public class RGBCalculator {

    public static int getColor(int red, int green, int blue) {
        return validate(red) * 65536
                + validate(green) * 256
                + validate(blue);
    }

    private static int validate(int color) {
        return Math.max(0, Math.min(255, color));
    }
}
