package edu.itau.vaga.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Handler global de exceções da aplicação.
 * Centraliza o tratamento de erros e padroniza as respostas HTTP.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Trata exceções de regras de negócio.
     * Retorna status 422 (Unprocessable Entity) sem corpo.
     * 
     * @param ex Exceção de negócio lançada
     * @return ResponseEntity com status 422
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Map<String, Object>> tratarRegraDeNegocio(BusinessException ex) {
        logger.error("Erro de negócio: {}", ex.getMessage());
        return ResponseEntity.status(422).build();
    }

    /**
     * Trata erros de parsing de JSON.
     * Retorna status 400 (Bad Request) sem corpo.
     * 
     * @param ex Exceção de parsing de JSON
     * @return ResponseEntity com status 400
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Void> handleJsonParseErrors(HttpMessageNotReadableException ex) {
        logger.error("Erro ao processar JSON: {}", ex.getMessage());
        return ResponseEntity.badRequest().build();
    }
}
