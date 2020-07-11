package finki.lab.formula.racing.port.rest;

import finki.lab.formula.racing.application.RaceCatalog;
import finki.lab.formula.racing.domain.model.Race;
import finki.lab.formula.racing.domain.model.RaceId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/races")
public class RaceCatalogController {

    private final RaceCatalog raceCatalog;

    public RaceCatalogController(RaceCatalog raceCatalog) {
        this.raceCatalog = raceCatalog;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Race> findById(@PathVariable("id") String raceId) {
        return raceCatalog.findById(new RaceId(raceId))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Race> findAll() {
        return raceCatalog.findAll();
    }
}
