package com.numarics.game.controller;

import com.numarics.game.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class GameControllerMvcTest {

    private MockMvc mockMvc;
    private GameController gameController;
    private GameService gameService;

    @BeforeEach
    void setUp() {
        gameService = mock(GameService.class);
        gameController = new GameController(gameService);
        mockMvc = MockMvcBuilders
                .standaloneSetup(gameController)
                .build();
    }

    @Test
    void should_start_game() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/v1/games/play")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"New game #1\", \"playerName\": \"Nikola\"}"))
                        .andExpect(status().isCreated());
    }

    @Test
    void should_get_game() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/games/game/1")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
    }

    @Test
    void should_update_game() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/v1/games/play/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"status\": \"FINISHED\"}"))
                        .andExpect(status().isOk());
    }

    @Test
    void should_delete_player() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/v1/games/game/1")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNoContent());
    }
}
