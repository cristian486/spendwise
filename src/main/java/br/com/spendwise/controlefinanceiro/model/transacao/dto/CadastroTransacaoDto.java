package br.com.spendwise.controlefinanceiro.model.transacao.dto;

import br.com.spendwise.controlefinanceiro.model.transacao.Categoria;
import br.com.spendwise.controlefinanceiro.model.transacao.TipoTransacao;
import br.com.spendwise.controlefinanceiro.model.transacao.Transacao;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CadastroTransacaoDto(@NotBlank(message = "Obrigatório o envio do usuário")
                                   String usuario,
                                   String grupoId,
                                   String descricao,
                                   @NotNull(message = "Obrigatório o envio da data")
                                   LocalDate data,
                                   @DecimalMin("0.01")
                                   @DecimalMax("9999999.99")
                                   @Digits(integer = 7, fraction = 2)
                                   @NotNull(message = "Obrigatório o envio do valor")
                                   BigDecimal valor,
                                   @NotNull(message = "Obrigatório o envio da categoria")
                                   Categoria categoria,
                                   @NotNull(message = "Obrigatório o envio do tipo da transação")
                                   TipoTransacao tipo) {

    public Transacao toModel() {
        return new Transacao( usuario, grupoId, descricao, data, valor, categoria, tipo);
    }
}
