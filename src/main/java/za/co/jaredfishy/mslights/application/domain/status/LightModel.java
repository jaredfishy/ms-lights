package za.co.jaredfishy.mslights.application.domain.status;

import za.co.jaredfishy.mslights.application.exception.UnknownLightModelException;

public enum LightModel {

    COLOR("color"),
    STRIPE("stripe"),
    CEILING("ceiling"),
    BSLAMP("bslamp");

    private String model;

    LightModel(String model) {
        this.model = model;
    }

    public static LightModel fromString(String model) {
        for (LightModel lightModel : LightModel.values()) {
            if (lightModel.name().equalsIgnoreCase(model) || lightModel.model.equalsIgnoreCase(model))
                return lightModel;
        }
        throw new UnknownLightModelException("Unknown light model \"" + model + "\"");
    }
}
