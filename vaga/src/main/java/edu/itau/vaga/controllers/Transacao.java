package edu.itau.vaga.controllers;

import edu.itau.vaga.TransacoesHistorico;
import edu.itau.vaga.dtos.TransacaoDto;
import edu.itau.vaga.services.TransacaoServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Transacao {
    @Autowired
    private TransacoesHistorico transacoesHistorico;

    @GetMapping("/")
    public List<TransacaoDto> get() {
        return transacoesHistorico.getTransacoes();
    }

    @PostMapping("/transacao")
    public ResponseEntity<Void> postTransacao(TransacaoDto transacao) {
        // Validação de transação
        TransacaoServices.postTransacao(transacao);
        transacoesHistorico.adicionarTransacao(transacao);
        return ResponseEntity.status(200).build();
    }
}
