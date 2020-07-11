package finki.lab.formula.racing.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import finki.lab.formula.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.io.Serializable;
@Embeddable
public class PlacementId extends DomainObjectId {

    public PlacementId() {
        super(DomainObjectId.randomId(PlacementId.class).toString());
    }

    @JsonCreator
    public PlacementId(String id) {
        super(id);
    }

}
