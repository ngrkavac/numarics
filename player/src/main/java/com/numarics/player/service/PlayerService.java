package com.numarics.player.service;

import com.numarics.player.domain.Player;

public interface PlayerService {

    Player registerPlayer(long gameId, String name);
    Player getPlayer(long id);
    Player getExistingPlayerOrRegister(long gameId, String name);
    void deletePlayer(long id);
}
