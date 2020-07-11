package finki.lab.formula.fia.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import finki.lab.formula.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class LeaderboardDriversId extends DomainObjectId {

    public LeaderboardDriversId() {
        super(DomainObjectId.randomId(LeaderboardDriversId.class).toString());
    }

    @JsonCreator
    public LeaderboardDriversId(String id) {
        super(id);
    }
}
