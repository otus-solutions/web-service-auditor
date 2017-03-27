package org.ccem.auditor.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@PrepareForTest({Auditor.class, SessionLog.class, LogEntry.class})
@RunWith(PowerMockRunner.class)
public class AuditorTest {
    private String body = "{\"email\":\"diogo.rosas.ferreira@gmail.com\",\"password\":\"XXXXXXXXXXXXXXXX\"}";
    private String auditorJson = "{\"date\":\"2017-03-15T19:58:31.306Z\",\"logEntries\":[{\"date\":\"2017-03-15T19:58:31.306Z\",\"ip\":\"143.54.220.56\",\"restURL\":\"localhost:8080/otus-rest\",\"body\":{\"email\":\"diogo.rosas.ferreira@gmail.com\"},\"sessionIdentifier\":{\"token\":\"651cas651891qcw51c3as51c\",\"secretKey\":[54,53,49,99,97,115,54,53,49,56,57,49,113,99,119,53,49,99,51,97,115,53,49,99],\"requestAddress\":\"143.54.220.57\"}}]}";
    private LocalDateTime date;
    private Auditor auditor;
    private LogEntry logEntry;

    @Before
    public void setUp() throws Exception {
        date = LocalDateTime.ofInstant(Instant.parse("2017-03-15T19:58:31.306Z"), ZoneId.of("UTC"));
        PowerMockito.mockStatic(LocalDateTime.class);
        PowerMockito.when(LocalDateTime.ofInstant(Mockito.any(), Mockito.any())).thenReturn(date);

        SessionLog sessionLog = new SessionLog();
        sessionLog.setRequestAddress("143.54.220.57");
        sessionLog.setToken("651cas651891qcw51c3as51c");
        sessionLog.setSecretKey("651cas651891qcw51c3as51c".getBytes());
        logEntry = new LogEntry("143.54.220.56", "localhost:8080/otus-rest", body, sessionLog);
        auditor = new Auditor();
        auditor.init();
        auditor.addEntry(logEntry);
    }

    @Test
    public void should_return_object_serialized() {
        Assert.assertEquals(auditorJson, Auditor.serialize(auditor));
    }

    @Test
    public void should_return_object_deserialized() {
        Auditor auditorDeserialized = Auditor.deserialize(auditorJson);
        Assert.assertEquals(date, auditorDeserialized.getDate());
        Assert.assertEquals(1, auditorDeserialized.getLogEntries().size());
    }
}
