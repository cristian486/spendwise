package br.com.spendwise.controlefinanceiro.controller;

import br.com.spendwise.controlefinanceiro.model.transacao.dto.AtualizarTransacaoDto;
import br.com.spendwise.controlefinanceiro.model.transacao.dto.CadastroTransacaoDto;
import br.com.spendwise.controlefinanceiro.model.transacao.dto.ListagemTransacaoDto;
import br.com.spendwise.controlefinanceiro.model.transacao.dto.OverviewTransacaoDto;
import br.com.spendwise.controlefinanceiro.service.TransacaoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/transacoes")
@AllArgsConstructor
public class TransacaoController {

    private final TransacaoService service;

    @GetMapping
    public ResponseEntity<Page<ListagemTransacaoDto>> listar(@PageableDefault(sort = "data", size = 15, direction = Sort.Direction.DESC) Pageable pageable, @RequestParam(name = "usuario") String usuario) {
        Page<ListagemTransacaoDto> listagem = service.listar(pageable, usuario);
        return ResponseEntity.status(HttpStatus.OK).body(listagem);
    }

    @GetMapping("/grupo")
    public ResponseEntity<Page<ListagemTransacaoDto>> listarTransacoesGrupo(@PageableDefault(sort = "data", size = 15, direction = Sort.Direction.DESC) Pageable pageable, @RequestParam(name = "grupoId") String grupoId) {
        Page<ListagemTransacaoDto> listagem = service.listaTransacoesGrupo(pageable, grupoId);
        return ResponseEntity.status(HttpStatus.OK).body(listagem);
    }

    @GetMapping("/overview")
    public ResponseEntity<OverviewTransacaoDto> overview(@RequestParam(name = "usuario") String usuario) {
        OverviewTransacaoDto overview = service.visaoGeral(usuario);
        return ResponseEntity.status(HttpStatus.OK).body(overview);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid CadastroTransacaoDto cadastroTransacaoDto) {
        service.cadastrar(cadastroTransacaoDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Transactional
    @PatchMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable("id") String id, @RequestBody @Valid AtualizarTransacaoDto atualizarTransacaoDto) {
        service.atualizar(id, atualizarTransacaoDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable("id") String id) {
        service.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
