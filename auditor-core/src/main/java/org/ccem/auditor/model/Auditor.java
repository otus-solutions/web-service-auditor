package org.ccem.auditor.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Auditor {
    private Date date;
    private Set<LogEntry> logEntries;

    public void addEntry(LogEntry logEntry) {
        logEntries.add(logEntry);
    }

    public void init() {
        date = new Date();
        logEntries = new HashSet<>();
    }

    public Boolean isEmpty(){
        return logEntries.isEmpty();
    }
}
