package finki.lab.formula.racing.application;


import com.sun.istack.NotNull;
import finki.lab.formula.racing.application.form.DriverPlacementForm;
import finki.lab.formula.racing.application.form.RaceForm;
import finki.lab.formula.racing.domain.events.DriverFinished;
import finki.lab.formula.racing.domain.events.RaceFinished;
import finki.lab.formula.racing.domain.model.DriverPlacement;
import finki.lab.formula.racing.domain.model.Race;
import finki.lab.formula.racing.domain.model.RaceId;
import finki.lab.formula.racing.domain.repository.DriverPlacementRepository;
import finki.lab.formula.racing.domain.repository.DriverRepository;
import finki.lab.formula.racing.domain.repository.RaceRepository;
import finki.lab.formula.racing.domain.repository.SeasonRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.time.Instant;
import java.util.*;

@Service
@Transactional
public class Racing {

    private final RaceRepository raceRepository;

    private final DriverPlacementRepository driverPlacementRepository;

    private final SeasonRepository seasonRepository;

    private final ApplicationEventPublisher applicationEventPublisher;

    private final Validator validator;

    public Racing(RaceRepository raceRepository, ApplicationEventPublisher applicationEventPublisher, Validator validator, DriverPlacementRepository driverRepository, SeasonRepository seasonRepository) {
        this.raceRepository = raceRepository;
        this.applicationEventPublisher = applicationEventPublisher;
        this.validator = validator;
        this.driverPlacementRepository = driverRepository;
        this.seasonRepository = seasonRepository;
    }

    public RaceId createRace(@NotNull RaceForm race){
        Objects.requireNonNull(race, "race must not be null");
        var constraintViolations = validator.validate(race);

        if ((constraintViolations).size() > 0) {
            throw new ConstraintViolationException("The OrderForm is not valid", constraintViolations);
        }
        var newRace = raceRepository.saveAndFlush(toDomainModel(race));
        applicationEventPublisher.publishEvent(new RaceFinished(newRace.id(), Instant.now()));
        newRace.getDriverPlacements().forEach(placement -> applicationEventPublisher.publishEvent(new DriverFinished(newRace.id(), placement.getPosition(), placement.getDriver().getId(), Instant.now())));
        return newRace.id();
    }

    @NotNull
    private Race toDomainModel(@NotNull RaceForm raceForm){
        var race = new Race(raceForm.getDate(), raceForm.getName(), raceForm.getSeasonObject(), toDomainModel(raceForm.getPlacements()));
        seasonRepository.saveAndFlush(race.getSeason());
        return race;
    }

    @NotNull Set<DriverPlacement> toDomainModel(@NotNull Set<DriverPlacementForm> driverPlacementForms){
        Set<DriverPlacement> placements = new HashSet<>();
        for(DriverPlacementForm placement : driverPlacementForms){
            DriverPlacement placment_save = new DriverPlacement(placement.getRace(), placement.getDriver(), placement.getPlaceObject());
            placements.add(placment_save);
        }
        return placements;
    }

    @NotNull
    public Optional<Race> findById(@NotNull RaceId raceId){
        Objects.requireNonNull(raceId, "Id must not be null");
        return raceRepository.findById(raceId);
    }
}
