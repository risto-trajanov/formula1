package finki.lab.formula.fia.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import finki.lab.formula.sharedkernel.domain.base.DomainObjectId;

public class SeasonId  extends DomainObjectId {

    public SeasonId() {
        super(DomainObjectId.randomId(SeasonId.class).toString());
    }

    @JsonCreator
    public SeasonId(String id) {
        super(id);
    }

}
