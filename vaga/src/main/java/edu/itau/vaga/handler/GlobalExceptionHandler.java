package edu.itau.vaga.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Map<String, Object>> tratarRegraDeNegocio(BusinessException ex) {
        return ResponseEntity.status(422).build();

    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Void> handleJsonParseErrors(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().build(); // ou outro status, se preferir
    }
}
