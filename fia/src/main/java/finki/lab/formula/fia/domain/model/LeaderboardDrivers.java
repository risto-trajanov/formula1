package finki.lab.formula.fia.domain.model;

import finki.lab.formula.sharedkernel.domain.base.AbstractEntity;
import finki.lab.formula.sharedkernel.domain.base.DomainObjectId;
import finki.lab.formula.sharedkernel.domain.placement.Place;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "leaderboard_drivers")
public class LeaderboardDrivers extends AbstractEntity<LeaderboardDriversId> {

    @Version
    private Long version;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;

    @ManyToOne
    @MapsId("season_id")
    @JoinColumn(name = "season_id")
    private Season season;

    @ManyToOne
    @MapsId("driver_id")
    @JoinColumn(name = "driver_id")
    private Set<DriverPlacement> placementDrivers;

    public LeaderboardDrivers() {
    }

    public LeaderboardDrivers(Season season, Set<DriverPlacement> driverPlacement){
        super(DomainObjectId.randomId(LeaderboardDriversId.class));
        this.season = season;
        this.placementDrivers = driverPlacement;
    }

    public Season getSeason() {
        return season;
    }

    public Set<DriverPlacement> getPlacementDrivers() {
        return placementDrivers;
    }
}
