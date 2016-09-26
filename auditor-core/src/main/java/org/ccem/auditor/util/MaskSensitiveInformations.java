package org.ccem.auditor.util;

import java.util.regex.Pattern;

public class MaskSensitiveInformations {
    private static String REGEX_PASSWORD = "\"(?i)(password|pwd|passwordConfirmation)\":\"[\\w\\p{Punct}&&[^&]]*?\"";

    public static String mask(String data){
        Pattern compile = Pattern.compile(REGEX_PASSWORD);
        return compile.matcher(data).replaceAll("\"$1\":\"XXXXXXXXXXXXXXXX\"");
    }
}
