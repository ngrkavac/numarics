package com.numarics.player.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class PlayerException extends RuntimeException {
    private String message;
    private HttpStatus httpStatus;

}
