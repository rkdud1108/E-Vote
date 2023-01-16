package com.evoting.Exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
@Slf4j
public class ExceptionController {

    // 400
    @ExceptionHandler({
            RuntimeException.class,
            ResponseStatusException.class,
    })
    public ResponseEntity<Object> BadRequestException(final RuntimeException ex) {
        log.warn("400 error", ex);
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    // 401
    @ExceptionHandler({ AccessDeniedException.class })
    public ResponseEntity handleAccessDeniedException(final AccessDeniedException ex) {
        log.warn("401 error", ex);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }

    // 500
    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleAll(final Exception ex) {
        log.info(ex.getClass().getName());
        log.error("500 error", ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}