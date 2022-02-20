package com.numarics.game.repository;

import com.numarics.game.domain.Game;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface GameRepository extends CrudRepository<Game, Long>  {

    Optional<Game> findByName(String name);
}
