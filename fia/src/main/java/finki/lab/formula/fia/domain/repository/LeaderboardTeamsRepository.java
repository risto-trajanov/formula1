package finki.lab.formula.fia.domain.repository;

import finki.lab.formula.fia.domain.model.LeaderboardTeams;
import finki.lab.formula.fia.domain.model.LeaderboardTeamsId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaderboardTeamsRepository extends JpaRepository<LeaderboardTeams, LeaderboardTeamsId> {
}
