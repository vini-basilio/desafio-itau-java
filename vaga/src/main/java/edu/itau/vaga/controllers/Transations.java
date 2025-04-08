package edu.itau.vaga.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Transations {
    @GetMapping("/")
    public String get(){
        return "Hello World";
    }
}
