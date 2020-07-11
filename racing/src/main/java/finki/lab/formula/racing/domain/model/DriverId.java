package finki.lab.formula.racing.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import finki.lab.formula.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class DriverId extends DomainObjectId {

    public DriverId() {
        super(DomainObjectId.randomId(DriverId.class).toString());
    }

    @JsonCreator
    public DriverId(String id) {
        super(id);
    }
}
