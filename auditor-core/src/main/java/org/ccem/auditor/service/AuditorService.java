package org.ccem.auditor.service;

import org.ccem.auditor.model.LogEntry;

public interface AuditorService {
    void log(LogEntry logEntryDto);
}
