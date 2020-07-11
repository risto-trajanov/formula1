package finki.lab.formula.fia.integration;

import com.fasterxml.jackson.annotation.JsonProperty;
import finki.lab.formula.fia.domain.model.Driver;
import finki.lab.formula.fia.domain.model.RaceId;
import finki.lab.formula.sharedkernel.domain.base.DomainEvent;
import finki.lab.formula.sharedkernel.domain.placement.Place;

import java.time.Instant;

public class DriverFinishedEvent implements DomainEvent {


    @JsonProperty("raceId")
    private final RaceId raceId;

    @JsonProperty("place")
    private final Place place;

    @JsonProperty("driver")
    private final Driver driver;

    @JsonProperty("occurredOn")
    private final Instant occurredOn;

    public DriverFinishedEvent(RaceId raceId, Place place, Driver driver, Instant occurredOn) {
        this.raceId = raceId;
        this.place = place;
        this.driver = driver;
        this.occurredOn = occurredOn;
    }

    @Override
    public Instant occurredOn() {
        return occurredOn;
    }
}