package edu.itau.vaga.services;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.itau.vaga.TransacoesHistorico;
import edu.itau.vaga.dtos.Estatisticas;
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

    public Estatisticas estatisticaTransacoes() {
        OffsetDateTime now = OffsetDateTime.now(ZoneOffset.of("-03:00")).plusMinutes(1);

        Estatisticas estatisticas = new Estatisticas();

        for (TransacaoDto transacao : transacoesHistorico.getTransacoes()) {

            if (transacao.getDataHora().isBefore(now)) {

                estatisticas.setCount(estatisticas.getCount() + 1);
                estatisticas.setSum(estatisticas.getSum() + transacao.getValor());
                estatisticas.setAvg(estatisticas.getAvg() + transacao.getValor());
                estatisticas.setMin(Math.min(estatisticas.getMin(), transacao.getValor()));
                estatisticas.setMax(Math.max(estatisticas.getMax(), transacao.getValor()));
            }
        }

        if (estatisticas.getCount() > 0) {
            estatisticas.setAvg(estatisticas.getSum() / estatisticas.getCount());
        }

        return estatisticas;
    }
}
