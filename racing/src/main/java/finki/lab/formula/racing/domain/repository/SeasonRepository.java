package finki.lab.formula.racing.domain.repository;

import finki.lab.formula.racing.domain.model.Season;
import finki.lab.formula.racing.domain.model.SeasonId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface  SeasonRepository extends JpaRepository<Season, SeasonId> {

    @Query(value = "SELECT s.start_date FROM season s", nativeQuery = true)
    List<Integer> findAllByseasonNumber();
}
