package br.com.spendwise.controlefinanceiro.model.graficos;

import br.com.spendwise.controlefinanceiro.model.transacao.Categoria;

import java.math.BigDecimal;

public record DadosGraficoDebitosDto(Categoria categoria, BigDecimal valor) {
}
