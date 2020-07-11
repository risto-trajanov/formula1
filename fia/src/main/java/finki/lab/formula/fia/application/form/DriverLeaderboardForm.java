package finki.lab.formula.fia.application.form;

import finki.lab.formula.fia.domain.model.DriverPlacement;
import finki.lab.formula.fia.domain.model.Season;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.TreeSet;

public class DriverLeaderboardForm {

    @NotNull
    @Valid
    private Season season = new Season();

    @NotNull
    @Valid
    private Set<DriverPlacementForm> placementDrivers = new TreeSet<>();

    public void setSeason(Season season) {
        this.season = season;
    }

    public void setPlacementDrivers(Set<DriverPlacementForm> placementDrivers) {
        this.placementDrivers = placementDrivers;
    }

    public Season getSeason() {
        return season;
    }

    public Set<DriverPlacementForm> getPlacementDrivers() {
        return placementDrivers;
    }
}
