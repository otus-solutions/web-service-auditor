package org.ccem.auditor.model;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class LogEntry {
    private LocalDateTime date;
    private String ip;
    private String restURL;
    private String method;
    private String body;
    private SessionLog sessionIdentifier;

    public LogEntry(String ip, String restURL, String method, String body, SessionLog sessionIdentifier) {
        this.ip = ip;
        this.restURL = restURL;
        this.method = method;
        this.body = body;
        this.sessionIdentifier = sessionIdentifier;
        this.date = LocalDateTime.ofInstant(Instant.now(), ZoneId.of("UTC"));
    }

    public LogEntry(String ip, String restURL, String body, String method, SessionLog sessionIdentifier, LocalDateTime date) {
        this.ip = ip;
        this.restURL = restURL;
        this.body = body;
        this.method = method;
        this.sessionIdentifier = sessionIdentifier;
        this.date = date;
    }

    public String getRestURL() {
        return restURL;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getIp() {
        return ip;
    }
}
