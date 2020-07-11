package finki.lab.formula.racing.application;

import finki.lab.formula.racing.domain.events.DriverFinished;
import finki.lab.formula.racing.domain.model.Driver;
import finki.lab.formula.racing.domain.model.Race;
import finki.lab.formula.racing.domain.model.RaceId;
import finki.lab.formula.racing.domain.repository.DriverRepository;
import finki.lab.formula.racing.domain.repository.RaceRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class RaceCatalog {

    private final RaceRepository raceRepository;
    private final DriverRepository driverRepository;

    public RaceCatalog(RaceRepository raceRepository, DriverRepository driverRepository) {
        this.raceRepository = raceRepository;
        this.driverRepository = driverRepository;
    }

    @NonNull
    public List<Race> findAll() {
        return raceRepository.findAll();
    }

    @NonNull
    public Optional<Race> findById(@NonNull RaceId raceId) {
        Objects.requireNonNull(raceId, "productId must not be null");
        return raceRepository.findById(raceId);
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onDriverPlacement(DriverFinished event) {
        Driver driver = driverRepository.findById(event.getDriverId()).orElseThrow(RuntimeException::new);
        Race race = raceRepository.findById(event.getRaceId()).orElseThrow(RuntimeException::new);
        race.addPlacementDriver(driver, event.getPlace());
        raceRepository.save(race);
    }
}

