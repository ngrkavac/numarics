package com.numarics.game.service;

import com.numarics.game.domain.Game;
import com.numarics.game.dto.GameCreateResultDTO;

public interface GameService {

    GameCreateResultDTO startGame(String playerName, String name);
    Game getGameById(long id);
    Game updateGameStatus(long id, String status);
    void deleteGame(long id);
}
