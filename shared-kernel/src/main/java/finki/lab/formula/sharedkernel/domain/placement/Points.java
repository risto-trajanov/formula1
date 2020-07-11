package finki.lab.formula.sharedkernel.domain.placement;

import finki.lab.formula.sharedkernel.domain.base.ValueObject;
import lombok.Getter;

@Getter
public class Points implements ValueObject {
    private final int amount;

    public Points(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}
