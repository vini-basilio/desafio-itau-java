package edu.itau.vaga.controllers;

import edu.itau.vaga.dtos.TransacaoDto;
import edu.itau.vaga.dtos.EstatisticasDto;
import edu.itau.vaga.services.TransacaoServices;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;

/**
 * Controller responsável por expor os endpoints da API de transações.
 * Gerencia as requisições HTTP e delega a lógica de negócio para o serviço.
 */
@RestController
public class Transacao {
    @Autowired
    private TransacaoServices transacaoServices;

    private static final Logger logger = LoggerFactory.getLogger(Transacao.class);

    /**
     * Endpoint para criar uma nova transação.
     * Valida o JSON recebido e delega para o serviço.
     * 
     * @param transacao DTO com os dados da transação
     * @return ResponseEntity com status 201 em caso de sucesso
     */
    @Operation(summary = "Registra uma nova transação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transação aceita"),
            @ApiResponse(responseCode = "400", description = "JSON inválido", content = @Content),
            @ApiResponse(responseCode = "422", description = "Transação inválida (valor negativo ou data futura)", content = @Content)
    })
    @PostMapping("/transacao")
    public ResponseEntity<Void> postTransacao(@Valid @RequestBody TransacaoDto transacao) {
        transacaoServices.postTransacao(transacao);
        logger.info("Transacao recebida: {}", transacao);
        return ResponseEntity.status(201).build();
    }

    /**
     * Endpoint para limpar o histórico de transações.
     * 
     * @return ResponseEntity com status 200 em caso de sucesso
     */
    @Operation(summary = "Remove todas as transações")
    @ApiResponse(responseCode = "200", description = "Histórico apagado com sucesso")
    @DeleteMapping("/transacao")
    public ResponseEntity<Void> deleteTransacao() {
        transacaoServices.limparHistorico();
        logger.info("Historico de transacoes limpo");
        return ResponseEntity.status(200).build();
    }

    /**
     * Endpoint para obter estatísticas das transações.
     * 
     * @param segundos Período em segundos para filtrar as transações (opcional)
     * @return ResponseEntity com as estatísticas das transações
     */
    @Operation(summary = "Retorna estatísticas das transações")
    @ApiResponse(responseCode = "200", description = "Estatísticas calculadas com sucesso")
    @GetMapping("/estatisticas")
    public ResponseEntity<EstatisticasDto> getEstatisticas(@RequestParam(required = false) Integer segundos) {
        EstatisticasDto estatisticas = transacaoServices.estatisticaTransacoes(segundos);
        logger.info("Estatisticas retornadas: {}", estatisticas);
        return ResponseEntity.ok(estatisticas);
    }
}
