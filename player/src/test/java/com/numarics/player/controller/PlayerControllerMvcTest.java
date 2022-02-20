package com.numarics.player.controller;

import com.numarics.player.service.PlayerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PlayerControllerMvcTest {

    private MockMvc mockMvc;
    private PlayerController playerController;
    private PlayerService playerService;

    @BeforeEach
    void setUp() {
        playerService = mock(PlayerService.class);
        playerController = new PlayerController(playerService);
        mockMvc = MockMvcBuilders
                .standaloneSetup(playerController)
                .build();
    }

    @Test
    void should_register_player() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/v1/players/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"gameId\": \"1\", \"playerName\": \"Nikola\"}"))
                        .andExpect(status().isCreated());
    }

    @Test
    void should_get_player() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/players/player?gameId=1&name=Nikola")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
    }

    @Test
    void should_delete_player() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/v1/players/player/1")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNoContent());
    }

}
