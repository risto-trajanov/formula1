package finki.lab.formula.racing;

import finki.lab.formula.racing.domain.model.*;
import finki.lab.formula.racing.domain.repository.DriverRepository;
import finki.lab.formula.racing.domain.repository.SeasonRepository;
import finki.lab.formula.racing.domain.repository.TeamRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Component
public class DataGenerator {

    private final DriverRepository driverRepository;

    private final TeamRepository teamRepository;

    private final SeasonRepository seasonRepository;

    public DataGenerator(DriverRepository driverRepository, TeamRepository teamRepository, SeasonRepository seasonRepository) {
        this.driverRepository = driverRepository;
        this.teamRepository = teamRepository;
        this.seasonRepository = seasonRepository;
    }

    @PostConstruct
    @Transactional
    public void generateData() {
        var teams = new ArrayList<Team>();
        if (teamRepository.findAll().size()==0) {
            teams.add(new Team(new TeamId("1"), "Ferrari"));
            teams.add(new Team(new TeamId("2"), "Mercedes"));
            teams.add(new Team(new TeamId("3"), "Red Bull"));
            teamRepository.saveAll(teams);
        }

        if(driverRepository.findAll().size() == 0){
            var drivers = new ArrayList<Driver>();
            var teams_rep = teamRepository.findAll();
            drivers.add(new Driver(new DriverId("1"), "Sebastian Vetel", teams_rep.get(0)));
            drivers.add(new Driver(new DriverId("2"), "Charles Leclerc", teams_rep.get(0)));
            drivers.add(new Driver(new DriverId("3"), "Lewis Hamilot", teams_rep.get(1)));
            drivers.add(new Driver(new DriverId("4"), "Valteri Botas", teams_rep.get(1)));
            drivers.add(new Driver(new DriverId("5"), "Max Verstapen", teams_rep.get(2)));
            drivers.add(new Driver(new DriverId("6"), "Alex Albon", teams_rep.get(2)));
            driverRepository.saveAll(drivers);
        }

        if(seasonRepository.findAll().size() == 0){
            var seassons = new ArrayList<Season>();
            seassons.add(new Season(new SeasonId("1"), 1));
            seassons.add(new Season(new SeasonId("2"), 2));
            seassons.add(new Season(new SeasonId("3"), 3));
            seasonRepository.saveAll(seassons);
        }


    }

}
