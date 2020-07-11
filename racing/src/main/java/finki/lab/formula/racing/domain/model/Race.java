package finki.lab.formula.racing.domain.model;

import finki.lab.formula.sharedkernel.domain.base.AbstractEntity;
import finki.lab.formula.sharedkernel.domain.base.DomainObjectId;
import finki.lab.formula.sharedkernel.domain.placement.Place;
import finki.lab.formula.sharedkernel.domain.placement.Points;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "race")
public class Race extends AbstractEntity<RaceId> {

    @Version
    private Long version;

    @AttributeOverride(name = "name", column = @Column(name = "name_of_race"))
    private String name;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;

    @Column(name = "date_of_race")
    private LocalDateTime raceDate;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<DriverPlacement> driverPlacements;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<TeamPlacement> teamPlacements;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "season_id")
    private Season season;

    public Race(){}

    public Race(@NotNull LocalDateTime raceDate, @NotNull String name, @NotNull Season season){
        super(DomainObjectId.randomId(RaceId.class));
        this.driverPlacements = new HashSet<>();
        setName(name);
        setRaceDate(raceDate);
        setSeason(season);
    }

    public Race(@NotNull LocalDateTime raceDate,
                @NotNull String name,
                @NotNull Season season,
                @NotNull Set<DriverPlacement> driverPlacements){
        super(DomainObjectId.randomId(RaceId.class));
        setName(name);
        setRaceDate(raceDate);
        setSeason(season);
        setDriverPlacements(driverPlacements);
        teamPlacements = new HashSet<>();
        setTeamPlacements();
    }

    public Race(@NotNull String name){
        super(DomainObjectId.randomId(RaceId.class));
        setName(name);
        this.raceDate = LocalDateTime.now();
        this.teamPlacements = new HashSet<>();
        this.driverPlacements = new HashSet<>();
        this.season = new Season();
    }

    public void setDriverPlacements(@NotNull Set<DriverPlacement> driverPlacements) {
        this.driverPlacements = driverPlacements;
    }

    private void setName(@NotNull String name){
        this.name = name;
    }

    private void setRaceDate(@NotNull LocalDateTime date){
        this.raceDate = date;
    }

    private void setSeason(Season season){
        this.season = season;
    }

    public Driver addPlacementDriver(@NotNull Driver driver, Place place){
        Objects.requireNonNull(driver, "driver must not be null");
        driverPlacements.add(new DriverPlacement(this, driver, place));
        setTeamPlacements();
        return driver;
    }

    private void setTeamPlacements(){
        if(!teamPlacements.isEmpty()){
            teamPlacements.clear();
        }

        HashMap<Team, Integer> teams = new LinkedHashMap<>();
        for(DriverPlacement placement : driverPlacements){
            Team team = placement.getDriversTeam();
            Integer points = driverPlacements.stream()
                    .filter(driverPlacement -> driverPlacement.getDriversTeam().equals(team))
                    .map(driverPlacement -> driverPlacement.getPosition().getPoints())
                    .mapToInt(value -> value).sum();
            teams.putIfAbsent(team, points);
        }
        teams.entrySet().stream()
                .forEach(entry -> teamPlacements.add(new TeamPlacement(this, entry.getKey(), new Points(entry.getValue()))));
    }

    public LocalDateTime getRaceDate() {
        return raceDate;
    }

    public Set<DriverPlacement> getDriverPlacements() {
        return driverPlacements;
    }

    public Set<TeamPlacement> getTeamPlacements() {
        return teamPlacements;
    }

    public Season getSeason() {
        return season;
    }

    public String getName() {
        return name;
    }
}
