package za.co.jaredfishy.mslights.application.util;

import org.json.JSONObject;
import za.co.jaredfishy.mslights.application.domain.command.Command;

public class CommandMessageCreator {

    private static long messageId = 182;

    public static void setMessageId(long id){
        messageId = id;
    }

    public static String getMessage(Command command) {
        JSONObject json = new JSONObject();
        json.put("id", messageId++);
        json.put("method", command.getMethod().getMethod());
        json.put("params", command.getParams());
        return json.toString();
    }
}
