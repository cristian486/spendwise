package br.com.spendwise.controlefinanceiro.model.transacao.dto;

import br.com.spendwise.controlefinanceiro.model.transacao.TipoTransacao;
import br.com.spendwise.controlefinanceiro.model.transacao.Transacao;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ListagemTransacaoDto(String id, LocalDate data, BigDecimal valor, TipoTransacao tipo) {

    public ListagemTransacaoDto(Transacao transacao) {
        this(transacao.getId(), transacao.getData(), transacao.getValor(), transacao.getTipo());
    }
}
