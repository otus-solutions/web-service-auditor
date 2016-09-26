package org.ccem.auditor.model;

import org.ccem.auditor.util.MaskSensitiveInformations;

import java.util.Date;

public class LogEntry {
    private Date date;
    private String ip;
    private String restURL;
    private String body;
    private SessionLog sessionIdentifier;

    public LogEntry(String ip, String restURL, String body, SessionLog sessionIdentifier) {
        this.ip = ip;
        this.restURL = restURL;
        this.body = MaskSensitiveInformations.mask(body);
        this.sessionIdentifier = sessionIdentifier;
        this.date = new Date();
    }
}
