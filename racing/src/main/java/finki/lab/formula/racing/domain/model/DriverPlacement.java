package finki.lab.formula.racing.domain.model;

import finki.lab.formula.sharedkernel.domain.base.AbstractEntity;
import finki.lab.formula.sharedkernel.domain.base.DomainObjectId;
import finki.lab.formula.sharedkernel.domain.placement.Place;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "driver_placement")
@Getter
public class DriverPlacement extends AbstractEntity<PlacementId> {

    @Version
    private Long version;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @MapsId("race_id")
    @JoinColumn(name = "race_id")
    Race race;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @MapsId("driver_id")
    @JoinColumn(name = "driver_id")
    Driver driver;

    @Column(name = "place")
    Place position;

    public DriverPlacement(){}

    public DriverPlacement(Race race, @NotNull Driver driver, @NotNull Place place){
        super(DomainObjectId.randomId(PlacementId.class));
        Objects.requireNonNull(race, "race must not be null");
        Objects.requireNonNull(driver, "driver must not be null");
        Objects.requireNonNull(place, "place must not be null");
        setDriver(driver);
        setRace(race);
        setPlace(place);
    }

    private void setRace(@NotNull Race race){
        this.race = race;
    }

    private void setDriver(@NotNull Driver driver){
        this.driver = driver;
    }

    private void setPlace(@NotNull Place place){
        if(place.getPlace() < 0 && place.getPlace() > 20){
            throw new IllegalArgumentException("The place must be between 1 and 20");
        }
        this.position = place;
    }

    public Team getDriversTeam(){
        return this.driver.getTeam();
    }

    public Place getPosition() {
        return position;
    }

    public Race getRace() {
        return race;
    }

    public Driver getDriver() {
        return driver;
    }
}
