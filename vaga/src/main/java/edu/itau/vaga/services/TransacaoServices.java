package edu.itau.vaga.services;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import edu.itau.vaga.dtos.TransacaoDto;
import edu.itau.vaga.handler.BusinessException;

public class TransacaoServices {
    public static void postTransacao(TransacaoDto transacao) {

        if (transacao.getValor() < 0) {
            throw new BusinessException("O valor da transação não pode ser negativo");
        }

        ZoneOffset offsetBrasil = ZoneOffset.of("-03:00");
        if (transacao.getDataHora().isAfter(OffsetDateTime.now(offsetBrasil))) {
            throw new BusinessException("A data da transação não pode ser futura");
        }
    }
}
