package org.ccem.auditor.model;

public class SessionLog {
    private String token;
    private byte[] secretKey;
    private String user;
    private String key;
    private String mode;
    private String requestAddress;

    public SessionLog(String user, String key, String mode) {
        this.user = user;
        this.key = key;
        this.mode = mode;
    }

    public SessionLog() {
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setSecretKey(byte[] secretKey) {
        this.secretKey = secretKey;
    }

    public void setRequestAddress(String requestAddress) {
        this.requestAddress = requestAddress;
    }
}
