package finki.lab.formula.racing.domain.model;

import finki.lab.formula.sharedkernel.domain.base.AbstractEntity;
import finki.lab.formula.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "season")
public class Season extends AbstractEntity<SeasonId> {

    @Version
    private Long version;

    @Column(name = "start_date")
    private int seasonNumber;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Race> races;

    public Season(){}

    public Season(SeasonId seasonId, @NotNull int seasonNumber){
        super(seasonId);
        this.seasonNumber = seasonNumber;
    }
    public Season(@NotNull int seasonNumber){
        super(DomainObjectId.randomId(SeasonId.class));
        this.seasonNumber = seasonNumber;
    }
    public int getSeasonNumber(){
        return seasonNumber;
    }

    public void addRace(@NotNull Race race){
        races.add(Objects.requireNonNull(race, "race can not be null"));
    }

}
