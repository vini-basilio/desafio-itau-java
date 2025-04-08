package edu.itau.vaga.controllers;

import edu.itau.vaga.dtos.TransacoDto;
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

    public String get() {
        return "Hello World";
    }

}
