package br.com.spendwise.controlefinanceiro.service;

import br.com.spendwise.controlefinanceiro.model.transacao.TipoTransacao;
import br.com.spendwise.controlefinanceiro.model.transacao.Transacao;
import br.com.spendwise.controlefinanceiro.model.transacao.dto.AtualizarTransacaoDto;
import br.com.spendwise.controlefinanceiro.model.transacao.dto.CadastroTransacaoDto;
import br.com.spendwise.controlefinanceiro.model.transacao.dto.ListagemTransacaoDto;
import br.com.spendwise.controlefinanceiro.model.transacao.dto.OverviewTransacaoDto;
import br.com.spendwise.controlefinanceiro.repository.TransacaoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.List;

@Service
@AllArgsConstructor
public class TransacaoService {

    private final TransacaoRepository repository;

    Transacao findTransacaoById(String id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("A transação requisitada não foi encontrada!"));
    }

    public void cadastrar(CadastroTransacaoDto cadastroTransacaoDto) {
        Transacao transacao = cadastroTransacaoDto.toModel();
        repository.save(transacao);
    }

    public void deletar(String id) {
        Transacao transacao = this.findTransacaoById(id);
        repository.delete(transacao);
    }

    public Page<ListagemTransacaoDto> listar(Pageable pageable, String usuario) {
        return repository.findAllByUsuario(pageable, usuario).map(ListagemTransacaoDto::new);
    }

    public void atualizar(String id, AtualizarTransacaoDto atualizarTransacaoDto) {
        Transacao transacao = this.findTransacaoById(id);
        transacao.atualizar(atualizarTransacaoDto);
    }

    public OverviewTransacaoDto visaoGeral(String usuario) {
        //LocalDate primeiroDoMesAtual = LocalDate.now().withDayOfMonth(1);
        //LocalDate ultimoDoMesAtual = LocalDate.now().withDayOfMonth(YearMonth.now().atEndOfMonth().getDayOfMonth());
        LocalDate primeiroDoMesAtual = LocalDate.of(2023, 9, 1);
        LocalDate ultimoDoMesAtual = LocalDate.of(2023, 9, 30);
        List<Transacao> transacoes = repository.visaoGeralDasTransacoesDoUsuario(usuario, primeiroDoMesAtual, ultimoDoMesAtual);

        BigDecimal valorGanho = transacoes.stream().filter(t -> t.getTipo().equals(TipoTransacao.CREDITO)).map(Transacao::getValor).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal valorGasto = transacoes.stream().filter(t -> t.getTipo().equals(TipoTransacao.DEBITO)).map(Transacao::getValor).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal valorAtual = valorGanho.subtract(valorGasto);
        return new OverviewTransacaoDto(valorAtual, valorGanho, valorGasto);
    }
}
