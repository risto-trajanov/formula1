package finki.lab.formula.racing.domain.repository;

import finki.lab.formula.racing.domain.model.Driver;
import finki.lab.formula.racing.domain.model.DriverId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, DriverId> {
}
