package com.numarics.game.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class GameException extends RuntimeException {
    private String message;
    private HttpStatus httpStatus;

}
