package finki.lab.formula.racing.domain.events;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import finki.lab.formula.racing.domain.model.SeasonId;
import finki.lab.formula.sharedkernel.domain.base.DomainEvent;
import org.springframework.lang.NonNull;

import java.time.Instant;
import java.util.Objects;

public class SeasonEnd implements DomainEvent {

    @JsonProperty("seasonid")
    private final SeasonId seasonId;
    @JsonProperty("occurredOn")
    private final Instant occurredOn;

    @JsonCreator
    public SeasonEnd(@JsonProperty("seasonId") @NonNull SeasonId seasonId,
                        @JsonProperty("occurredOn") @NonNull Instant occurredOn) {
        this.seasonId = Objects.requireNonNull(seasonId, "seasonId must not be null");
        this.occurredOn = Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }

    @NonNull
    public SeasonId orderId() {
        return seasonId;
    }

    @Override
    @NonNull
    public Instant occurredOn() {
        return occurredOn;
    }
}
