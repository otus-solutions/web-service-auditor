package org.ccem.auditor;

import org.ccem.auditor.model.Auditor;
import org.ccem.auditor.model.LogEntry;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ErrorEvent {
    private static final String MESSAGE = "[Auditor Log - Error saving event data]";
    private static final String LOGGER_TYPE = "application";
    private static final Logger logger = Logger.getLogger(LOGGER_TYPE);

    public static void register(Auditor auditor, Exception e) {
        logger.log(Level.SEVERE, ErrorEvent.builderMessage(e));

        auditor.getLogEntries().stream().forEach(logEntry ->
                logger.log(Level.SEVERE, ErrorEvent.builderDetails(logEntry))
        );
    }

    public static String builderMessage(Exception e) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(MESSAGE);
        stringBuilder.append(" ");
        stringBuilder.append(builderExceptionMessage(e));
        return stringBuilder.toString();
    }

    public static String builderExceptionMessage(Exception e) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("(");
        stringBuilder.append(e.getMessage());
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public static String builderDetails(LogEntry logEntry) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        stringBuilder.append("(");
        stringBuilder.append("Action:");
        stringBuilder.append(logEntry.getRestURL());
        stringBuilder.append(")");
        stringBuilder.append("(");
        stringBuilder.append("Date:");
        stringBuilder.append(logEntry.getDate());
        stringBuilder.append(")");
        stringBuilder.append("(");
        stringBuilder.append("IP:");
        stringBuilder.append(logEntry.getIp());
        stringBuilder.append(")");
        stringBuilder.append("]");

        return stringBuilder.toString();
    }
}
