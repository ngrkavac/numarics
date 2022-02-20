package com.numarics.game.controller;

import com.numarics.game.domain.Game;
import com.numarics.game.dto.GameCreateDTO;
import com.numarics.game.dto.GameCreateResultDTO;
import com.numarics.game.dto.GameUpdateDTO;
import com.numarics.game.service.GameService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/games")
@Slf4j
public class GameController {

    private final GameService gameService;

    @PostMapping(value = "play")
    public @ResponseBody
    ResponseEntity<GameCreateResultDTO> startGame(@RequestBody GameCreateDTO gameCreateDTO) {
        log.info("Start game: {}", gameCreateDTO.getName());
        GameCreateResultDTO game = gameService.startGame(gameCreateDTO.getPlayerName(), gameCreateDTO.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(game);
    }

    @GetMapping(value = "game/{id}")
    public @ResponseBody
    ResponseEntity<Game> getGame(@PathVariable long id) {
        log.info("Get game: {} details", id);
        Game game = gameService.getGameById(id);
        return ResponseEntity.status(HttpStatus.OK).body(game);
    }

    @PutMapping(value = "play/{id}")
    public @ResponseBody
    ResponseEntity<Game> updateGame(@PathVariable long id, @RequestBody GameUpdateDTO gameUpdateDTO) {
        log.info("Update game: {}", id);
        Game game = gameService.updateGameStatus(id, gameUpdateDTO.getStatus());
        return ResponseEntity.status(HttpStatus.OK).body(game);
    }

    @DeleteMapping(value = "game/{id}")
    public @ResponseBody
    ResponseEntity<Void> deleteGame(@PathVariable long id) {
        log.info("Delete game: {}", id);
        gameService.deleteGame(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
