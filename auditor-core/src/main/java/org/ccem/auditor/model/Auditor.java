package org.ccem.auditor.model;

import com.google.gson.GsonBuilder;
import org.ccem.auditor.adapter.InstantAdapter;
import org.ccem.auditor.adapter.LogEntryAdapter;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

public class Auditor {
    private Instant date;
    private Set<LogEntry> logEntries;

    public void addEntry(LogEntry logEntry) {
        logEntries.add(logEntry);
    }

    public void init() {
        date = Instant.now();
        logEntries = new HashSet<>();
    }

    public Boolean readyToPersist(){
        return !logEntries.isEmpty();
    }

    public static Auditor deserialize(String auditorJson) {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(LogEntry.class, new LogEntryAdapter());
        builder.registerTypeAdapter(Instant.class, new InstantAdapter());
        return builder.create().fromJson(auditorJson, Auditor.class);
    }

    public static String serialize(Auditor auditor) {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(LogEntry.class, new LogEntryAdapter());
        builder.registerTypeAdapter(Instant.class, new InstantAdapter());
        return builder.create().toJson(auditor);
    }

    public Instant getDate() {
        return date;
    }

    public Set<LogEntry> getLogEntries() {
        return logEntries;
    }
}
