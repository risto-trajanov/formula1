package finki.lab.formula.racing.domain.model;

import finki.lab.formula.sharedkernel.domain.base.AbstractEntity;
import lombok.Getter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Table(name = "team")
public class Team extends AbstractEntity<TeamId> {

    @Version
    private Long version;

    @AttributeOverride(name = "name", column = @Column(name = "name_of_team"))
    private String name;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Driver> drivers;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<TeamPlacement> teamPlacements;

    public Team(){

    }

    public Team(TeamId teamId, String name){
        super(teamId);
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public Set<Driver> getDrivers() {
        return drivers;
    }

    public Set<TeamPlacement> getTeamPlacements() {
        return teamPlacements;
    }
}
