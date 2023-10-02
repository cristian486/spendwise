package br.com.spendwise.controlefinanceiro.model.transacao.dto;

import br.com.spendwise.controlefinanceiro.model.transacao.Categoria;
import br.com.spendwise.controlefinanceiro.model.transacao.TipoTransacao;

import java.math.BigDecimal;
import java.time.LocalDate;

public record AtualizarTransacaoDto(String descricao, LocalDate data, BigDecimal valor, Categoria categoria, TipoTransacao tipo) {
}
