package finki.lab.formula.fia.domain.model;

import finki.lab.formula.sharedkernel.domain.base.AbstractEntity;
import finki.lab.formula.sharedkernel.domain.placement.Place;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "leaderboard_teams")
public class LeaderboardTeams extends AbstractEntity<LeaderboardTeamsId> {

    @Version
    private Long version;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;

    @ManyToOne
    @MapsId("season_id")
    @JoinColumn(name = "season_id")
    Season season;

    @ManyToOne
    @MapsId("team_id")
    @JoinColumn(name = "team_id")
    private Set<TeamPlacement> team;

    public LeaderboardTeams() {
    }
    @Column(name = "place")
    Place place;

}
