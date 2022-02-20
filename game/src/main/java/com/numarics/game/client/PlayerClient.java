package com.numarics.game.client;

import com.numarics.game.client.dto.PlayerDTO;
import com.numarics.game.client.dto.PlayerRegisteredDTO;
import com.numarics.game.exception.GameException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class PlayerClient {

    @Value("${player.url.base}")
    public String baseUrl;

    @Value("${player.url.version}")
    public String version;

    private static final String GET_PLAYER_ENDPOINT = "players/player";
    private static final String SLASH = "/";

    private final RestTemplate restTemplate;

    public PlayerClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public PlayerRegisteredDTO getRegisteredPlayer(PlayerDTO playerDTO) {
        StringBuilder url = new StringBuilder(baseUrl).append(SLASH)
                .append(version)
                .append(SLASH)
                .append(GET_PLAYER_ENDPOINT);

        String uriBuilder = UriComponentsBuilder.fromUriString(url.toString())
                .queryParam("name", playerDTO.getName())
                .queryParam("gameId", playerDTO.getGameId())
                .build()
                .toUriString();

        try {
            RequestEntity<PlayerDTO> request =
                    new RequestEntity<>(null, buildHeaders(), HttpMethod.GET, new URI(uriBuilder));

            return restTemplate.exchange(request, PlayerRegisteredDTO.class).getBody();

        } catch(Exception e) {
            throw new GameException("Failed to process request: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    private HttpHeaders buildHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
