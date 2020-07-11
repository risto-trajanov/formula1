package finki.lab.formula.racing.domain.model;
import finki.lab.formula.sharedkernel.domain.base.AbstractEntity;
import lombok.Getter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "driver")
@Getter
public class Driver extends AbstractEntity<DriverId> {

    @Version
    private Long version;


    @AttributeOverride(name = "name", column = @Column(name = "name_of_driver"))
    private String name;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<DriverPlacement> placements;

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    public Driver(){
    }

    public Driver(DriverId driverId, String name){
        super(driverId);
        this.name = name;
    }

    public Driver(DriverId driverId, String name, Team team){
        super(driverId);
        this.name = name;
        this.team = team;
    }

    public Team getTeam() {
        return team;
    }

    public String getName() {
        return name;
    }

    public Set<DriverPlacement> getPlacements() {
        return placements;
    }
}
