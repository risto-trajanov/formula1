package finki.lab.formula.sharedkernel.domain.base;

import finki.lab.formula.sharedkernel.infra.eventlog.StoredDomainEvent;

import java.util.List;

public interface RemoteEventLog {
    List<StoredDomainEvent> events();
}
