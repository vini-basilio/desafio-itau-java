package edu.itau.vaga.controllers;

import edu.itau.vaga.dtos.TransacaoDto;
import edu.itau.vaga.services.TransacaoServices;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Transacao {
    @Autowired
    private TransacaoServices transacaoServices;

    @GetMapping("/")
    public List<TransacaoDto> get() {
        return transacaoServices.getTransacoes();
    }

    @PostMapping("/transacao")
    public ResponseEntity<Void> postTransacao(@Valid @RequestBody TransacaoDto transacao) {
        // Validação de transação
        transacaoServices.postTransacao(transacao);

        return ResponseEntity.status(201).build();
    }

}
