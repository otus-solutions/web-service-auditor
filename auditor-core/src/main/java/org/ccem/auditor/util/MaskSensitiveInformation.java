package org.ccem.auditor.util;

import com.google.gson.JsonObject;

public class MaskSensitiveInformation {

    public static JsonObject mask(JsonObject data){
        data.remove("password");
        data.remove("pwd");
        data.remove("passwordConfirmation");
        return data;
    }
}
