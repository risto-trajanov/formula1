package finki.lab.formula.racing.domain.repository;

import finki.lab.formula.racing.domain.model.Race;
import finki.lab.formula.racing.domain.model.RaceId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RaceRepository  extends JpaRepository<Race, RaceId> {
}
