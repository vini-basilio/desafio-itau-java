package edu.itau.vaga.controllers;

import edu.itau.vaga.dtos.TransacaoDto;
import edu.itau.vaga.services.TransacaoServices;
import jakarta.validation.Valid;

import java.util.DoubleSummaryStatistics;
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

@RestController
public class Transacao {
    @Autowired
    private TransacaoServices transacaoServices;

    private static final Logger logger = LoggerFactory.getLogger(Transacao.class);

    @GetMapping("/")
    public List<TransacaoDto> get() {
        return transacaoServices.getTransacoes();
    }

    @PostMapping("/transacao")
    public ResponseEntity<Void> postTransacao(@Valid @RequestBody TransacaoDto transacao) {
        transacaoServices.postTransacao(transacao);
        logger.info("Transacao recebida: {}", transacao);

        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/transacao")
    public ResponseEntity<Void> deleteTransacao() {
        transacaoServices.limparHistorico();
        logger.info("Historico de transacoes limpo");
        return ResponseEntity.status(200).build();
    }

    @GetMapping("/estatisticas")
    public ResponseEntity<DoubleSummaryStatistics> getEstatisticas(@PathVariable(required = false) Integer segundos) {
        DoubleSummaryStatistics estatisticas = transacaoServices.estatisticaTransacoes(segundos);
        logger.info("Estatisticas retornadas: {}", estatisticas);
        return ResponseEntity.ok(estatisticas);
    }
}
