package finki.lab.formula.fia.application;

import finki.lab.formula.fia.domain.model.Race;
import finki.lab.formula.fia.domain.model.RaceId;

import java.util.List;

public interface RaceCatalog {

    List<Race> findAll();

    Race findById(RaceId raceId);
}
