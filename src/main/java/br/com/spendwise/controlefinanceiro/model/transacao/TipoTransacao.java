package br.com.spendwise.controlefinanceiro.model.transacao;

import br.com.spendwise.controlefinanceiro.model.conta.Conta;

import java.math.BigDecimal;

public enum TipoTransacao {
    CREDITO {
        @Override
        public void alterar(Conta conta, BigDecimal valor) {
            conta.modificarCredito(valor);
        }
    }, DEBITO {
        @Override
        public void alterar(Conta conta, BigDecimal valor) {
            conta.modificarDebito(valor);
        }
    };

    public abstract void alterar(Conta conta, BigDecimal valor);
}
