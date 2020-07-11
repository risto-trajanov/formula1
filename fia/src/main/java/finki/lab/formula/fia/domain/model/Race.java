package finki.lab.formula.fia.domain.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

public class Race {

    private String name;

    private LocalDateTime raceDate;

    private Set<DriverPlacement> driverPlacements;

    private Set<TeamPlacement> teamPlacements;

    private Season season;
}
