package finki.lab.formula.racing.domain.repository;

import finki.lab.formula.racing.domain.model.Team;
import finki.lab.formula.racing.domain.model.TeamId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, TeamId> {
}
