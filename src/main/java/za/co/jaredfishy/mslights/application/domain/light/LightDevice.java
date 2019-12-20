package za.co.jaredfishy.mslights.application.domain.light;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class LightDevice {

    private final LightBrand brand;
    private final String id;
    private final String name;
    private final String model;
    private final String firmwareVersion;
    private final List<String> support;

    public LightDevice(LightBrand brand, String id, String name, String model, String firmwareVersion, List<String> support) {
        this.brand = brand;
        this.id = id;
        this.name = name;
        this.model = model;
        this.firmwareVersion = firmwareVersion;
        this.support = support;
    }

    @JsonProperty
    public LightBrand getBrand() {
        return brand;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("model")
    public String getModel() {
        return model;
    }

    @JsonIgnore
    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    @JsonIgnore
    public List<String> getSupport() {
        return support;
    }
}
