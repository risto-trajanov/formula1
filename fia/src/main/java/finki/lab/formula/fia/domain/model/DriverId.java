package finki.lab.formula.fia.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import finki.lab.formula.sharedkernel.domain.base.DomainObjectId;

public class DriverId extends DomainObjectId {

    public DriverId() {
        super(DomainObjectId.randomId(DriverId.class).toString());
    }

    @JsonCreator
    public DriverId(String id) {
        super(id);
    }
}
