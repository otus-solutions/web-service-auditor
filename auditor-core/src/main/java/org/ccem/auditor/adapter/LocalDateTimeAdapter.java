package org.ccem.auditor.adapter;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeAdapter implements JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {

    private final DateTimeFormatter iso8601Format;

    public LocalDateTimeAdapter() {
        this.iso8601Format = DateTimeFormatter.ISO_DATE_TIME;
    }

    @Override
    public LocalDateTime deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2)
            throws JsonParseException {
        if (!(arg0 instanceof JsonPrimitive)) {
            throw new JsonParseException("The date should be a String value");
        }

        return LocalDateTime.parse(arg0.getAsString(), iso8601Format);
    }

    @Override
    public JsonElement serialize(LocalDateTime arg0, Type arg1, JsonSerializationContext arg2) {
		/*
		 * In LocalDateTime toString method return a string without the
		 * character Z at the end. To keep the same string that was received by
		 * new Date().toISOString() on front-end the arg0 was converted to
		 * Instant.
		 */

        return arg2.serialize(arg0.toInstant(ZoneOffset.UTC).toString());
    }

}

