package org.ccem.auditor.service;

import org.ccem.auditor.AuditorContext;
import org.ccem.auditor.model.Auditor;
import org.ccem.auditor.model.LogEntry;
import org.ccem.auditor.persistence.AuditorDao;

import javax.ejb.Asynchronous;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class AuditorServiceBean implements AuditorService {
    @Inject
    private AuditorDao auditorDao;

    @Inject
    private AuditorContext auditorContext;

    @Override
    @Asynchronous
    public void log(LogEntry logEntry) {
        auditorContext.addLogEntry(logEntry);
    }

    @Schedule(hour = "*/1", info = "Persist Auditor Log")
    public void persist() {
        Auditor auditor = auditorContext.getAuditor();
        if (!auditor.isEmpty()) {
            auditorDao.persist(auditor);
            auditorContext.init();
        }
    }
}
