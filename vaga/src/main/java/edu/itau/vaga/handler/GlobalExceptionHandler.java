package edu.itau.vaga.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Map<String, Object>> tratarRegraDeNegocio(BusinessException ex) {
        Map<String, Object> erro = new HashMap<>();
        erro.put("timestamp", Instant.now());
        erro.put("status", 422);
        erro.put("erro", ex.getMessage());

        return ResponseEntity.unprocessableEntity().body(erro);
    }
}
