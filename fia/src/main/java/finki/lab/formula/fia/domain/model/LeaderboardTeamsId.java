package finki.lab.formula.fia.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import finki.lab.formula.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;
@Embeddable
public class LeaderboardTeamsId extends DomainObjectId {

    public LeaderboardTeamsId() {
        super(DomainObjectId.randomId(LeaderboardTeamsId.class).toString());
    }

    @JsonCreator
    public LeaderboardTeamsId(String id) {
        super(id);
    }
}
