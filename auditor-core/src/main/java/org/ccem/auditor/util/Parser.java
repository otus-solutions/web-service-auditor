package org.ccem.auditor.util;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.ccem.auditor.model.SessionLog;

public class Parser {

    public static JsonObject body(JsonElement body){
        JsonObject bodyJsonObject = new Gson().fromJson(body.getAsString(), JsonElement.class).getAsJsonObject();
        return MaskSensitiveInformation.mask(bodyJsonObject);
    }

    public static SessionLog sessionLog(JsonElement sessionLog){
        return new Gson().fromJson(sessionLog.toString(), SessionLog.class);
    }
}
