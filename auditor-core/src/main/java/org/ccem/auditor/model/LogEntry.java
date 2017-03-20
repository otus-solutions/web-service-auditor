package org.ccem.auditor.model;

import java.time.Instant;

public class LogEntry {
    private Instant date;
    private String ip;
    private String restURL;
    private String body;
    private SessionLog sessionIdentifier;

    public LogEntry(String ip, String restURL, String body, SessionLog sessionIdentifier) {
        this.ip = ip;
        this.restURL = restURL;
        this.body = body;
        this.sessionIdentifier = sessionIdentifier;
        this.date = Instant.now();
    }

    public LogEntry(String ip, String restURL, String body, SessionLog sessionIdentifier, Instant date) {
        this.ip = ip;
        this.restURL = restURL;
        this.body = body;
        this.sessionIdentifier = sessionIdentifier;
        this.date = date;
    }
}
