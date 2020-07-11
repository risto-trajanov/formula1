package finki.lab.formula.racing.domain.repository;

import finki.lab.formula.racing.domain.model.DriverPlacement;
import finki.lab.formula.racing.domain.model.PlacementId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverPlacementRepository  extends JpaRepository<DriverPlacement, PlacementId> {
}
