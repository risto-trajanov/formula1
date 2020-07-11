package finki.lab.formula.racing.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import finki.lab.formula.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class TeamId extends DomainObjectId {
    public TeamId() {
        super(DomainObjectId.randomId(TeamId.class).toString());
    }

    @JsonCreator
    public TeamId(String id) {
        super(id);
    }
}
