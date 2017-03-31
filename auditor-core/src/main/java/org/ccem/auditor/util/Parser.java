package org.ccem.auditor.util;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.ccem.auditor.model.SessionLog;
import org.ccem.auditor.processing.PrepareInformation;

public class Parser {

    public static JsonObject body(JsonElement element) {
        if (!element.isJsonNull()) {
            String body = element.getAsString();

            if (!body.isEmpty()) {
                JsonObject bodyJsonObject = new Gson().fromJson(body, JsonElement.class).getAsJsonObject();
                return PrepareInformation.prepare(bodyJsonObject);
            }
        }
        return new JsonObject();
    }

    public static SessionLog sessionLog(JsonElement sessionLog) {
        if (!sessionLog.isJsonNull()) {
            return new Gson().fromJson(sessionLog.toString(), SessionLog.class);
        } else {
            return new SessionLog();
        }
    }
}
