package com.numarics.game.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(GameException.class)
    public ResponseEntity<Object> handleGameException(GameException e, WebRequest request) {
        log.error("Unexpected error: {}", e.getMessage());
        return handleExceptionInternal(e, e.getMessage(),
                new HttpHeaders(), e.getHttpStatus(), request);
    }
}
