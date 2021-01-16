package za.co.jaredfishy.mslights.application.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONSerializer {

    public static String serialize(Object object, String defaultString) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            if(defaultString!=null)
                return defaultString;
            return object.toString();
        }
    }
}
