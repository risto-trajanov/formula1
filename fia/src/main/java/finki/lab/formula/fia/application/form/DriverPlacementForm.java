package finki.lab.formula.fia.application.form;

import finki.lab.formula.fia.domain.model.Driver;
import finki.lab.formula.fia.domain.model.Race;
import finki.lab.formula.sharedkernel.domain.placement.Place;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class DriverPlacementForm implements Serializable {

    @NotNull
    @Valid
    private Driver driver;

    @NotNull
    @Valid
    private Race race;

    @NotNull
    private Place place;

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
