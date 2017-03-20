package org.ccem.auditor.adapter;

import com.google.gson.*;
import org.ccem.auditor.model.LogEntry;
import org.ccem.auditor.model.SessionLog;
import org.ccem.auditor.util.Parser;

import java.lang.reflect.Type;
import java.time.Instant;

public class LogEntryAdapter implements JsonDeserializer<LogEntry>, JsonSerializer<LogEntry> {
    @Override
    public LogEntry deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        Instant date = Instant.parse(jsonObject.get("date").getAsString());
        String ip = jsonObject.get("ip").getAsString();
        String restURL = jsonObject.get("restURL").getAsString();
        String body = jsonObject.get("body").toString();
        SessionLog sessionLog = Parser.sessionLog(jsonObject.get("sessionIdentifier"));

        LogEntry logEntry = new LogEntry(ip,restURL, body, sessionLog, date);
        return logEntry;
    }

    @Override
    public JsonElement serialize(LogEntry logEntry, Type type, JsonSerializationContext jsonSerializationContext) {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Instant.class, new InstantAdapter());

        JsonElement logEntryJsonElement = builder.create().toJsonTree(logEntry);
        JsonObject logEntryJsonObject = logEntryJsonElement.getAsJsonObject();

        JsonElement body = logEntryJsonObject.get("body");
        logEntryJsonObject.add("body", Parser.body(body));
        return logEntryJsonObject;
    }
}
