package edu.itau.vaga;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import edu.itau.vaga.dtos.TransacaoDto;

@Component
public class TransacoesHistorico {
    public List<TransacaoDto> transacoes = new ArrayList<>();

    public void adicionarTransacao(TransacaoDto transacao) {
        transacoes.add(transacao);
    }

    public List<TransacaoDto> getTransacoes() {
        return transacoes;
    }

    public void limparHistorico() {
        if (!transacoes.isEmpty()) {
            transacoes.clear();
        }
    }
}
