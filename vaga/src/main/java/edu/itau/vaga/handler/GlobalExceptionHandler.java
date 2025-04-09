package edu.itau.vaga.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Map<String, Object>> tratarRegraDeNegocio(BusinessException ex) {
        logger.error("Erro de negócio: {}", ex.getMessage());
        return ResponseEntity.status(422).build();

    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Void> handleJsonParseErrors(HttpMessageNotReadableException ex) {
        logger.error("Erro ao processar JSON: {}", ex.getMessage());
        return ResponseEntity.badRequest().build(); // ou outro status, se preferir
    }
}
