package com.numarics.player.service;

import com.numarics.player.domain.Player;
import com.numarics.player.exception.PlayerException;
import com.numarics.player.repository.PlayerRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    @Override
    public Player registerPlayer(long gameId, String name) {
        return createPlayer(gameId, name);
    }

    @Override
    public Player getPlayer(long id) {
        return playerRepository.findById(id).orElseThrow(
                () -> new PlayerException(String.format("Game id: {%s} not found", id), HttpStatus.NOT_FOUND));
    }

    @Override
    public Player getExistingPlayerOrRegister(long gameId, String name) {
        Optional<Player> foundPlayer = getPlayerByName(name, gameId);
        return foundPlayer.orElseGet(() -> this.registerPlayer(gameId, name));
    }

    @Override
    public void deletePlayer(long id) {
        playerRepository.deleteById(id);
    }

    private Optional<Player> getPlayerByName(String name, long gameId) {
        return playerRepository.findByNameAndGameId(name, gameId);
    }

    private Player createPlayer(long gameId, String name) {
        Player player = new Player();
        player.setGameId(gameId);
        player.setName(name);
        return playerRepository.save(player);
    }
}
