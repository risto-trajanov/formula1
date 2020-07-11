package finki.lab.formula.racing.domain.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import finki.lab.formula.racing.domain.model.RaceId;
import finki.lab.formula.sharedkernel.domain.base.DomainEvent;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Objects;

public class RaceFinished implements DomainEvent {

    @JsonProperty("raceId")
    private final RaceId raceId;
    @JsonProperty("occurredOn")
    private final Instant occurredOn;


    public RaceFinished(@JsonProperty("raceId") @NotNull RaceId raceId,
                        @JsonProperty("occurredOn") @NotNull Instant occurredOn){
        this.raceId = Objects.requireNonNull(raceId, "raceId must not be null");
        this.occurredOn = Objects.requireNonNull(occurredOn, "occuredOn must not be null");
    }

    @NotNull
    public RaceId raceId(){
        return raceId;
    }
    @Override
    @NotNull
    public Instant occurredOn() {
        return occurredOn;
    }
}
