package finki.lab.formula.fia.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import finki.lab.formula.sharedkernel.domain.base.DomainEvent;
import finki.lab.formula.sharedkernel.infra.eventlog.RemoteEventTranslator;
import finki.lab.formula.sharedkernel.infra.eventlog.StoredDomainEvent;

import java.util.Optional;

public class RaceFinishedEventTranslator implements RemoteEventTranslator {

    private final ObjectMapper objectMapper;

    RaceFinishedEventTranslator(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean supports(StoredDomainEvent remoteEvent) {
        return remoteEvent.domainEventClassName().equals("finki.lab.formula.fia.integration.RaceFinishedEvent");
    }

    @Override
    public Optional<DomainEvent> translate(StoredDomainEvent remoteEvent) {
        return Optional.of(remoteEvent.toDomainEvent(objectMapper, RaceFinishedEvent.class));
    }
}