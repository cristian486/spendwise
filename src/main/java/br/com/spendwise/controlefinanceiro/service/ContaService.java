package br.com.spendwise.controlefinanceiro.service;

import br.com.spendwise.controlefinanceiro.model.conta.Conta;
import br.com.spendwise.controlefinanceiro.model.transacao.TipoTransacao;
import br.com.spendwise.controlefinanceiro.model.transacao.dto.OverviewTransacaoDto;
import br.com.spendwise.controlefinanceiro.repository.ContaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
@AllArgsConstructor
public class ContaService {

    private final ContaRepository repository;

    Optional<Conta> findaContaByUsuario(String usuario) {
        return repository.findByUsuario(usuario);
    }

    Conta cadastrar(String usuario) {
        Conta conta = new Conta(usuario, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
        return repository.save(conta);
    }

    void modificarSaldo(String usuario, TipoTransacao tipo, BigDecimal valor) {
        AtomicReference<Conta> conta = new AtomicReference<>();
        Optional<Conta> optionalConta = this.findaContaByUsuario(usuario);

        optionalConta.ifPresentOrElse(conta::set, () -> {
            Conta contaTemp = this.cadastrar(usuario);
            conta.set(contaTemp);
        });

        tipo.alterar(conta.get(), valor);
    }

    public OverviewTransacaoDto visaoGeral(String usuario) {
        Conta conta = this.findaContaByUsuario(usuario).orElse(new Conta());
        return new OverviewTransacaoDto(conta.getSaldoAtual(), conta.getCredito(), conta.getDebito());
    }
}
