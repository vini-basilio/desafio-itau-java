package edu.itau.vaga.services;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.itau.vaga.TransacoesHistorico;
import edu.itau.vaga.dtos.TransacaoDto;
import edu.itau.vaga.handler.BusinessException;

@Component
public class TransacaoServices {
    @Autowired
    private TransacoesHistorico transacoesHistorico;

    public void postTransacao(TransacaoDto transacao) {

        if (transacao.getValor() < 0) {
            throw new BusinessException("O valor da transação não pode ser negativo");
        }

        ZoneOffset offsetBrasil = ZoneOffset.of("-03:00");
        if (transacao.getDataHora().isAfter(OffsetDateTime.now(offsetBrasil))) {
            throw new BusinessException("A data da transação não pode ser futura");
        }

        transacoesHistorico.adicionarTransacao(transacao);
    }

    public List<TransacaoDto> getTransacoes() {
        return transacoesHistorico.getTransacoes();
    }

    public void limparHistorico() {
        transacoesHistorico.limparHistorico();
    }
}
