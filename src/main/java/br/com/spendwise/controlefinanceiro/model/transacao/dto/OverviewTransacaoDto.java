package br.com.spendwise.controlefinanceiro.model.transacao.dto;

import java.math.BigDecimal;

public record OverviewTransacaoDto(BigDecimal valorAtual, BigDecimal credito, BigDecimal debito) {
}
