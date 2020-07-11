package finki.lab.formula.fia.domain.repository;

import finki.lab.formula.fia.domain.model.LeaderboardDrivers;
import finki.lab.formula.fia.domain.model.LeaderboardDriversId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaderboardDriversRepository extends JpaRepository<LeaderboardDrivers, LeaderboardDriversId> {
}
