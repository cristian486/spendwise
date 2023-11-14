package br.com.spendwise.controlefinanceiro.model.transacao.dto;

import br.com.spendwise.controlefinanceiro.model.transacao.TipoTransacao;
import br.com.spendwise.controlefinanceiro.model.transacao.Transacao;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ListagemTransacaoDto(String id, String usuario, String grupoId, String descricao, LocalDate data, BigDecimal valor, TipoTransacao tipo) {

    public ListagemTransacaoDto(Transacao transacao) {
        this(transacao.getId(), transacao.getUsuario(), transacao.getGrupoId(), transacao.getDescricao(), transacao.getData(), transacao.getValor(), transacao.getTipo());
    }
}
