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
import java.util.List;

@Service
@AllArgsConstructor
public class TransacaoService {

    private final TransacaoRepository repository;
    private final ContaService contaService;

    Transacao findTransacaoById(String id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("A transação requisitada não foi encontrada!"));
    }

    public Page<ListagemTransacaoDto> listar(Pageable pageable, String usuario) {
        return repository.findAllByUsuarioAndGrupoIdIsNull(pageable, usuario).map(ListagemTransacaoDto::new);
    }

    List<Transacao> listar(String usuario) {
        return repository.findAllByUsuarioLikeIgnoreCase(usuario);
    }

    public Page<ListagemTransacaoDto> listaTransacoesGrupo(Pageable pageable, String grupoId) {
        return repository.findAllByGrupoId(pageable, grupoId).map(ListagemTransacaoDto::new);
    }

    public void cadastrar(CadastroTransacaoDto cadastroTransacaoDto) {
        Transacao transacao = cadastroTransacaoDto.toModel();
        repository.save(transacao);
        contaService.modificarSaldo(transacao.getUsuario(), transacao.getTipo(), transacao.getValor());
    }

    public void deletar(String id) {
        Transacao transacao = this.findTransacaoById(id);
        repository.delete(transacao);
        contaService.modificarSaldo(transacao.getUsuario(), transacao.getTipo(), transacao.getValor());
    }
}
