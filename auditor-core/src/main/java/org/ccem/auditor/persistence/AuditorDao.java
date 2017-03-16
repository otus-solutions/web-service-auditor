package org.ccem.auditor.persistence;

import org.ccem.auditor.model.Auditor;

public interface AuditorDao {
    void persist(Auditor auditor);
}
