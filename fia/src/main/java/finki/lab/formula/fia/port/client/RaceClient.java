package finki.lab.formula.fia.port.client;

import finki.lab.formula.fia.application.RaceCatalog;
import finki.lab.formula.fia.domain.model.Race;
import finki.lab.formula.fia.domain.model.RaceId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;

@Service
public class RaceClient implements RaceCatalog {

    private static final Logger LOGGER = LoggerFactory.getLogger(RaceClient.class);

    private final RestTemplate restTemplate;
    private final String serverUrl;

    RaceClient(@Value("${app.racing.url}") String serverUrl,
                         @Value("${app.racing.connect-timeout-ms}") int connectTimeout,
                         @Value("${app.racing.read-timeout-ms}") int readTimeout) {
        this.serverUrl = serverUrl;
        restTemplate = new RestTemplate();
        var requestFactory = new SimpleClientHttpRequestFactory();
        // Never ever do a remote call without a finite timeout!
        requestFactory.setConnectTimeout(connectTimeout);
        requestFactory.setReadTimeout(readTimeout);
        restTemplate.setRequestFactory(requestFactory);
    }

    private UriComponentsBuilder uri() {
        return UriComponentsBuilder.fromUriString(serverUrl);
    }

    @Override
    public List<Race> findAll() {
        try {
            return restTemplate.exchange(uri().path("/api/races").build().toUri(), HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<Race>>() {
                    }).getBody();
        } catch (Exception ex) {
            LOGGER.error("Error retrieving races", ex);
            return Collections.emptyList();
        }
    }

    @Override
    public Race findById(RaceId id) {
        try {
            return restTemplate.exchange(uri().path("/api/races/"+id.getId()).build().toUri(), HttpMethod.GET, null,
                    new ParameterizedTypeReference<Race>() {
                    }).getBody();
        } catch (Exception ex) {
            LOGGER.error("Error retrieving race id", ex);
            return null;
        }
    }
}
