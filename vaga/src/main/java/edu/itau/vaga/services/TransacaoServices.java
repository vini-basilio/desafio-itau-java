package edu.itau.vaga.services;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.itau.vaga.TransacoesHistorico;

import edu.itau.vaga.dtos.TransacaoDto;
import edu.itau.vaga.dtos.EstatisticasDto;
import edu.itau.vaga.handler.BusinessException;

/**
 * Serviço responsável por gerenciar as transações e suas regras de negócio.
 * Mantém o histórico de transações e calcula estatísticas.
 */
@Component
public class TransacaoServices {
    @Autowired
    private TransacoesHistorico transacoesHistorico;

    /**
     * Valida e registra uma nova transação.
     * 
     * @param transacao DTO contendo os dados da transação
     * @throws BusinessException se a transação violar alguma regra de negócio
     */
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

    /**
     * Calcula estatísticas das transações dentro de um período.
     * 
     * @param segundos Período em segundos para filtrar as transações (opcional,
     *                 padrão 60s)
     * @return Estatísticas das transações no período
     */
    public EstatisticasDto estatisticaTransacoes(Integer segundos) {
        int segundosFiltro = 60;
        if (!(segundos == null)) {
            segundosFiltro = segundos;
        }
        OffsetDateTime now = OffsetDateTime.now(ZoneOffset.of("-03:00")).minusSeconds(segundosFiltro);

        var filteredTransacoes = transacoesHistorico.getTransacoes()
                .stream()
                .filter(transacao -> transacao.getDataHora().isAfter(now))
                .mapToDouble(TransacaoDto::getValor)
                .summaryStatistics();

        return new EstatisticasDto(
                filteredTransacoes.getCount(),
                filteredTransacoes.getSum(),
                filteredTransacoes.getAverage(),
                filteredTransacoes.getMin(),
                filteredTransacoes.getMax());
    }
}
