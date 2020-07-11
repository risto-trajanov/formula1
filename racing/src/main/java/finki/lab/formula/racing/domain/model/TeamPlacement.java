package finki.lab.formula.racing.domain.model;

import finki.lab.formula.sharedkernel.domain.base.AbstractEntity;
import finki.lab.formula.sharedkernel.domain.base.DomainObjectId;
import finki.lab.formula.sharedkernel.domain.placement.Place;
import finki.lab.formula.sharedkernel.domain.placement.Points;
import net.bytebuddy.pool.TypePool;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "team_placement")
public class TeamPlacement extends AbstractEntity<PlacementId> {

    @Version
    private Long version;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;


    @ManyToOne(cascade = CascadeType.PERSIST)
    @MapsId("race_id")
    @JoinColumn(name = "race_id")
    Race race;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @MapsId("team_id")
    @JoinColumn(name = "team_id")
    Team team;

    @Column(name = "place")
    Points points;

    public TeamPlacement(){}

    public TeamPlacement(@NotNull Race race, @NotNull Team team, @NotNull Points points){
        super(DomainObjectId.randomId(PlacementId.class));
        Objects.requireNonNull(race, "race must not be null");
        Objects.requireNonNull(team, "team must not be null");
        Objects.requireNonNull(points, "place must not be null");
        setTeam(team);
        setRace(race);
        setPoints(points);
    }

    private void setRace(@NotNull Race race){
        this.race = race;
    }

    private void setTeam(@NotNull Team team){
        this.team = team;
    }

    private void setPoints(@NotNull Points points){
        if(points.getAmount() < 0){
            throw new IllegalArgumentException("Points must be larger than 0");
        }
        this.points = points;
    }
}
