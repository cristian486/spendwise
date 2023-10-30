package br.com.spendwise.controlefinanceiro.service;

import br.com.spendwise.controlefinanceiro.model.grupo.Grupo;
import br.com.spendwise.controlefinanceiro.model.grupo.dto.CadastroGrupoDto;
import br.com.spendwise.controlefinanceiro.model.grupo.dto.DetalhesGrupoDto;
import br.com.spendwise.controlefinanceiro.model.grupo.dto.MovimentoGrupoDto;
import br.com.spendwise.controlefinanceiro.model.grupo.dto.ListagemGrupoDto;
import br.com.spendwise.controlefinanceiro.model.integrante.Integrante;
import br.com.spendwise.controlefinanceiro.model.transacao.Transacao;
import br.com.spendwise.controlefinanceiro.repository.GrupoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GrupoService {

    private final IntegranteService integranteService;
    private final GrupoRepository grupoRepository;

    private final TransacaoService transacaoService;


    Grupo findGrupoById(String id) {
        return grupoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Grupo requisitado não foi encontrado"));
    }

    public List<ListagemGrupoDto> listar(String usuario) {
        List<ListagemGrupoDto> listagem = new ArrayList<>();
        Optional<Integrante> integrante = integranteService.findByUsuario(usuario);
        integrante.ifPresent(integrant -> {
            List<Grupo> grupos = grupoRepository.findAllByIntegrantesContains(integrant);
            grupos.forEach(g -> {
                List<Transacao> listar = transacaoService.listar(g.getId());
                BigDecimal saldoDoGrupo = listar.stream().map(Transacao::getValor).reduce(BigDecimal.ZERO, BigDecimal::add);
                listagem.add(new ListagemGrupoDto(g.getId(), g.getNome(), saldoDoGrupo));
            });
        });

        return listagem;
    }

    public void cadastrar(CadastroGrupoDto dadosCadastro) {
        Grupo grupo = new Grupo(null, dadosCadastro.nome(), dadosCadastro.dono(), dadosCadastro.usuario(), new ArrayList<>());
        grupoRepository.save(grupo);
        Integrante integrante = integranteService.cadastrar(grupo, dadosCadastro.usuario());
        grupo.adicionarIntegrante(integrante);
    }

    public void adicionarAoGrupo(MovimentoGrupoDto movimentoGrupoDto) {
        Grupo grupo = this.findGrupoById(movimentoGrupoDto.grupoId());
        integranteService.cadastrar(grupo, movimentoGrupoDto.usuario());
    }

    public DetalhesGrupoDto detalhar(String id) {
        Grupo grupo = this.findGrupoById(id);
        return new DetalhesGrupoDto(grupo);
    }
}
