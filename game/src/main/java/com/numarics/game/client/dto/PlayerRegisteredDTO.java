package com.numarics.game.client.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerRegisteredDTO {
    private long id;
    private String name;
    private long gameId;
}
