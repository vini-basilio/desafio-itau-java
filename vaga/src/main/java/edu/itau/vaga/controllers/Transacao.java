package edu.itau.vaga.controllers;

import edu.itau.vaga.dtos.TransacoDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Transacao {
    @GetMapping("/")
    public String get() {
        return "Hello World";
    }

}
