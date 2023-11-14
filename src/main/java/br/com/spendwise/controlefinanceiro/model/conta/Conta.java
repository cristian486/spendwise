package br.com.spendwise.controlefinanceiro.model.conta;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Conta {

    @Id
    @UuidGenerator
    private String id;
    private String usuario;
    private BigDecimal saldoAtual;
    private BigDecimal debito;
    private BigDecimal credito;

    public Conta(String usuario, BigDecimal saldoAtual, BigDecimal debito, BigDecimal credito) {
        this.usuario = usuario;
        this.saldoAtual = saldoAtual;
        this.debito = debito;
        this.credito = credito;
    }

    private void modificarSaldo(BigDecimal valor) {
        this.saldoAtual = this.saldoAtual.add(valor);
    }

    public void modificarDebito(BigDecimal valor) {
        BigDecimal novoValor = valor.compareTo(BigDecimal.ZERO) <= 0 ? valor : valor.negate();
        this.debito = this.debito.add(valor);
        this.modificarSaldo(novoValor);
    }

    public void modificarCredito(BigDecimal valor) {
        BigDecimal novoValor = valor.compareTo(BigDecimal.ZERO) >= 0 ? valor : valor.negate();
        this.credito = this.credito.add(valor);
        this.modificarSaldo(novoValor);
    }
}
