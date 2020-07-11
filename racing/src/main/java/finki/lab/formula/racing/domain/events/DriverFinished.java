package finki.lab.formula.racing.domain.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import finki.lab.formula.racing.domain.model.Driver;
import finki.lab.formula.racing.domain.model.DriverId;
import finki.lab.formula.racing.domain.model.RaceId;
import finki.lab.formula.sharedkernel.domain.base.DomainEvent;
import finki.lab.formula.sharedkernel.domain.placement.Place;

import java.time.Instant;

public class DriverFinished implements DomainEvent {


    @JsonProperty("raceId")
    private final RaceId raceId;

    @JsonProperty("place")
    private final Place place;

    @JsonProperty("driver")
    private final DriverId driverId;

    @JsonProperty("occurredOn")
    private final Instant occurredOn;

    public DriverFinished(RaceId raceId, Place place, DriverId driverId, Instant occurredOn) {
        this.raceId = raceId;
        this.place = place;
        this.driverId = driverId;
        this.occurredOn = occurredOn;
    }

    @Override
    public Instant occurredOn() {
        return occurredOn;
    }

    public RaceId getRaceId() {
        return raceId;
    }

    public Place getPlace() {
        return place;
    }

    public DriverId getDriverId() {
        return driverId;
    }

    public Instant getOccurredOn() {
        return occurredOn;
    }
}
