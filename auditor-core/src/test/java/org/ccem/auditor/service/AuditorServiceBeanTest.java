package org.ccem.auditor.service;

import org.ccem.auditor.AuditorContext;
import org.ccem.auditor.model.Auditor;
import org.ccem.auditor.model.LogEntry;
import org.ccem.auditor.persistence.AuditorDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({LogEntry.class, AuditorServiceBean.class})
public class AuditorServiceBeanTest {

    @InjectMocks
    private AuditorServiceBean auditorService;

    @Mock
    private AuditorDao auditorDaoBean;

    @Mock
    private AuditorContext auditorContext;

    @Mock
    private LogEntry logEntry;

    @Mock
    private Auditor auditor;

    @Test
    public void method_log_should_add_new_log_to_context() throws Exception {
        PowerMockito.whenNew(LogEntry.class).withAnyArguments().thenReturn(logEntry);
        auditorService.log(logEntry);
        Mockito.verify(auditorContext).addLogEntry(logEntry);
    }

    @Test
    public void method_persist_should_verify_if_exist_log(){
        Mockito.when(auditorContext.getAuditor()).thenReturn(auditor);
        Mockito.when(auditor.readyToPersist()).thenReturn(Boolean.TRUE);
        auditorService.persist();
        Mockito.verify(auditor).readyToPersist();
    }

    @Test
    public void method_persist_should_persist_only_when_exist_data(){
        Mockito.when(auditorContext.getAuditor()).thenReturn(auditor);
        Mockito.when(auditor.readyToPersist()).thenReturn(Boolean.FALSE);
        auditorService.persist();
        Mockito.verify(auditorDaoBean, Mockito.never()).persist(auditor);
        Mockito.verify(auditorContext, Mockito.never()).init();
    }
}
