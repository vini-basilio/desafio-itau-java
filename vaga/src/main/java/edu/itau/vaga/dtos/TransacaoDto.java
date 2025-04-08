package edu.itau.vaga.dtos;

import java.time.OffsetDateTime;

public record TransacaoDto(double valor,
        OffsetDateTime dataHora) {

    public double getValor() {
        return valor;
    }

    public OffsetDateTime getDataHora() {
        return dataHora;
    }

}
