package org.ccem.auditor.processing;

import com.google.gson.JsonObject;

import java.util.Arrays;
import java.util.List;

public class PrepareInformation {
    private static List<String> sensitiveWords = Arrays.asList("password", "pwd", "passwordConfirmation");
    private static List<String> reservedWords = Arrays.asList("$loki", "$$hashKey");

    public static JsonObject prepare(JsonObject data){
        sensitiveWords.stream().forEach(word -> data.remove(word));
        reservedWords.stream().forEach(word -> data.remove(word));
        return data;
    }
}
