package com.numarics.player.controller;

import com.numarics.player.domain.Player;
import com.numarics.player.dto.PlayerRegisterDTO;
import com.numarics.player.service.PlayerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/players")
@Slf4j
public class PlayerController {

    private final PlayerService playerService;

    @PostMapping(value = "register")
    public @ResponseBody
    ResponseEntity<Player> registerPlayer(@RequestBody PlayerRegisterDTO playerRegisterDTO) {
        log.info("Register player: {}", playerRegisterDTO.getPlayerName());
        Player player = playerService.registerPlayer(Long.parseLong(playerRegisterDTO.getGameId()), playerRegisterDTO.getPlayerName());
        return ResponseEntity.status(HttpStatus.CREATED).body(player);
    }

    @GetMapping(value = "player")
    public @ResponseBody
    ResponseEntity<Player> getPlayer(@RequestParam long gameId, @RequestParam String name) {
        log.info("Get player: {} registered for game: {}", name, gameId);
        Player player = playerService.getExistingPlayerOrRegister(gameId, name);
        return ResponseEntity.status(HttpStatus.OK).body(player);
    }

    @DeleteMapping(value = "player/{id}")
    public @ResponseBody
    ResponseEntity<Void> deletePlayer(@PathVariable long id) {
        log.info("Delete player: {}", id);
        playerService.deletePlayer(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
