package com.numarics.game.dto;

import com.numarics.game.domain.GameStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameCreateResultDTO {

    private long gameId;
    private String gameName;
    private GameStatus gameStatus;
    private long playerId;
    private String playerName;
}
