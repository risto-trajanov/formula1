package finki.lab.formula.fia.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import finki.lab.formula.sharedkernel.domain.base.DomainObjectId;

public class RaceId extends DomainObjectId {

    public RaceId() {
        super(DomainObjectId.randomId(RaceId.class).toString());
    }

    @JsonCreator
    public RaceId(String id) {
        super(id);
    }


}