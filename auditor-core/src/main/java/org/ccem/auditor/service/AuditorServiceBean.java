package org.ccem.auditor.service;

import org.ccem.auditor.AuditorContext;
import org.ccem.auditor.ErrorEvent;
import org.ccem.auditor.model.Auditor;
import org.ccem.auditor.model.LogEntry;
import org.ccem.auditor.persistence.AuditorDao;

import javax.ejb.Asynchronous;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.logging.Logger;

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

    @Schedule(hour = "*", minute = "*/5" ,info = "Persist Auditor Log")
    public void persist() {
        Auditor auditor = auditorContext.getAuditor();
        if (auditor.readyToPersist()) {
            try{
                auditorDao.persist(auditor);
            }catch (Exception e){
                ErrorEvent.register(auditor, e);
            }finally {
                auditorContext.init();
            }
        }
    }
}
