package za.co.jaredfishy.mslights.application.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class YeelightCommandRequest {

    private String ip;
    private List<String> ipList;
    private long id;
    private String method;
    private List<Object> params;

    @JsonCreator
    public YeelightCommandRequest(
            @JsonProperty("ip") String ip,
            @JsonProperty("ip_list") List<String> ipList,
            @JsonProperty("id") long id,
            @JsonProperty("method") String method,
            @JsonProperty("params") List<Object> params
    ) {
        this.ip = ip;
        this.ipList = ipList;
        this.id = id;
        this.method = method;
        this.params = params;
    }

    public String getIp() {
        return ip;
    }

    public List<String> getIpList() {
        return ipList;
    }

    public long getId() {
        return id;
    }

    public String getMethod() {
        return method;
    }

    public List<Object> getParams() {
        return params;
    }
}
