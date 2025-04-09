package edu.itau.vaga.controllers;

import edu.itau.vaga.dtos.TransacaoDto;
import edu.itau.vaga.dtos.EstatisticasDto;
import edu.itau.vaga.services.TransacaoServices;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
     * Endpoint para listar todas as transações.
     * 
     * @return Lista de todas as transações registradas
     */
    @GetMapping("/")
    public List<TransacaoDto> get() {
        return transacaoServices.getTransacoes();
    }

    /**
     * Endpoint para criar uma nova transação.
     * Valida o JSON recebido e delega para o serviço.
     * 
     * @param transacao DTO com os dados da transação
     * @return ResponseEntity com status 201 em caso de sucesso
     */
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
    @GetMapping("/estatisticas")
    public ResponseEntity<EstatisticasDto> getEstatisticas(@PathVariable(required = false) Integer segundos) {
        EstatisticasDto estatisticas = transacaoServices.estatisticaTransacoes(segundos);
        logger.info("Estatisticas retornadas: {}", estatisticas);
        return ResponseEntity.ok(estatisticas);
    }
}
