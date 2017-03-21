package org.ccem.auditor;

import org.ccem.auditor.model.Auditor;
import org.ccem.auditor.model.LogEntry;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AuditorContext {
    private Auditor auditor;

    @PostConstruct
    public void init(){
        auditor = new Auditor();
        auditor.init();
    }

    public void addLogEntry(LogEntry logEntry){
        auditor.addEntry(logEntry);
    }

    public Auditor getAuditor() {
        return auditor;
    }
}
