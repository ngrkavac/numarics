package com.numarics.player.repository;

import com.numarics.player.domain.Player;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PlayerRepository extends CrudRepository<Player, Long> {

    Optional<Player> findByNameAndGameId(String name, long gameId);
}
