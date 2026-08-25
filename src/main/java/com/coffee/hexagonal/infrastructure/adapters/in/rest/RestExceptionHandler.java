package com.coffee.hexagonal.infrastructure.adapters.in.rest;

import com.coffee.hexagonal.domain.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.Map;

/**
 * Traduce las excepciones de dominio (ej. InsufficientInventoryException) en
 * respuestas HTTP 400. Esta traducción es responsabilidad del adaptador
 * primario, no del dominio ni del caso de uso.
 */
@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Map<String, Object>> handleBusinessException(BusinessException ex) {
        Map<String, Object> body = Map.of(
                "timestamp", Instant.now().toString(),
                "error", "Pedido rechazado",
                "message", ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }
}
