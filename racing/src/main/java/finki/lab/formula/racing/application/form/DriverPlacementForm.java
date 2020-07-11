package finki.lab.formula.racing.application.form;

import finki.lab.formula.racing.domain.model.Driver;
import finki.lab.formula.racing.domain.model.DriverId;
import finki.lab.formula.racing.domain.model.Race;
import finki.lab.formula.sharedkernel.domain.placement.Place;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class DriverPlacementForm implements Serializable {

    @NotNull
    @Valid
    private Driver driver = new Driver(new DriverId("100000"), "Driver");

    @NotNull
    @Valid
    private Race race = new Race("Grand Prix");

    @NotNull
    private Place place = new Place(1);

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public void setPlace(Integer place) {
        this.place = new Place(place);
    }

    public Driver getDriver() {
        return driver;
    }

    public Race getRace() {
        return race;
    }

    public Integer getPlace() {
        return place.getPlace();
    }

    public Place getPlaceObject(){
        return this.place;
    }
}
