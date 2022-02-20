package com.numarics.game.service;

import com.numarics.game.client.PlayerClient;
import com.numarics.game.client.dto.PlayerDTO;
import com.numarics.game.client.dto.PlayerRegisteredDTO;
import com.numarics.game.domain.Game;
import com.numarics.game.domain.GameStatus;
import com.numarics.game.dto.GameCreateResultDTO;
import com.numarics.game.exception.GameException;
import com.numarics.game.repository.GameRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class GameServiceImpl implements GameService {

    private GameRepository gameRepository;
    private PlayerClient playerClient;

    @Override
    public GameCreateResultDTO startGame(String playerName, String name) {
        Optional<Game> existingGame = gameRepository.findByName(name);
        Game game = existingGame.isPresent() ? existingGame.get() : createNewGame(name);

        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setGameId(game.getId());
        playerDTO.setName(playerName);
        PlayerRegisteredDTO playerRegisteredDTO = playerClient.getRegisteredPlayer(playerDTO);

        GameCreateResultDTO gameCreateResultDTO = new GameCreateResultDTO();
        gameCreateResultDTO.setGameId(game.getId());
        gameCreateResultDTO.setGameName(game.getName());
        gameCreateResultDTO.setGameStatus(game.getStatus());
        gameCreateResultDTO.setPlayerId(playerRegisteredDTO.getId());
        gameCreateResultDTO.setPlayerName(playerRegisteredDTO.getName());

        return gameCreateResultDTO;

    }

    @Override
    public Game getGameById(long id) {
        return gameRepository.findById(id).orElseThrow(
                () -> new GameException(String.format("Game id: {%s} not found", id), HttpStatus.NOT_FOUND));
    }

    @Override
    public Game updateGameStatus(long id, String status) {
        Game game = gameRepository.findById(id).orElseThrow(
                () -> new GameException(String.format("Game id: {%s} not found", id), HttpStatus.NOT_FOUND));
        GameStatus gameStatus = GameStatus.valueOf(status);
        game.setStatus(gameStatus);
        game.setUpdatedAt(LocalDateTime.now());
        return game;
    }

    @Override
    public void deleteGame(long id) {
        Game game = gameRepository.findById(id).orElseThrow(
                () -> new GameException(String.format("Game id: {%s} not found", id), HttpStatus.NOT_FOUND));
        gameRepository.delete(game);
    }

    private Game createNewGame(String name) {
        Game game = new Game();
        game.setName(name);
        game.setStatus(GameStatus.NEW);
        game.setCreatedAt(LocalDateTime.now());
        return gameRepository.save(game);
    }
}
