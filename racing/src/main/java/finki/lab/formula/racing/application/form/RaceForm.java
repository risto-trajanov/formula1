package finki.lab.formula.racing.application.form;

import finki.lab.formula.racing.domain.model.Season;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class RaceForm implements Serializable {

    @NotNull
    private String name = new String("Grand Prix");

    @NotNull
    private LocalDateTime date = LocalDateTime.now();

    @NotNull
    private Season season = new Season(0);

    @Valid
    @NotNull
    private Set<DriverPlacementForm> placements = new HashSet<>();

    public String getName() {
        return name;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Integer getSeason() {
        return season.getSeasonNumber();
    }
    public Season getSeasonObject() {
        return season;
    }

    public Set<DriverPlacementForm> getPlacements() {
        return placements;
    }

    public void setPlacements(Set<DriverPlacementForm> placements) {
        this.placements = placements;
    }

    public void setSeason(Integer season) {
        this.season = new Season(season);
    }

    public void setName(String name) {
        this.name = name;
    }
}
