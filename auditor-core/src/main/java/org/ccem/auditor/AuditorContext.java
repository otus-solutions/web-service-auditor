package org.ccem.auditor;

import org.ccem.auditor.model.Auditor;
import org.ccem.auditor.model.LogEntry;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class AuditorContext {
    @Inject
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
