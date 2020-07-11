package finki.lab.formula.fia.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import finki.lab.formula.sharedkernel.domain.base.DomainObjectId;

public class TeamId extends DomainObjectId {
    public TeamId() {
        super(DomainObjectId.randomId(TeamId.class).toString());
    }

    @JsonCreator
    public TeamId(String id) {
        super(id);
    }
}
