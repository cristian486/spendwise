package br.com.spendwise.controlefinanceiro.controller;

import br.com.spendwise.controlefinanceiro.model.grupo.dto.CadastroGrupoDto;
import br.com.spendwise.controlefinanceiro.model.grupo.dto.DetalhesGrupoDto;
import br.com.spendwise.controlefinanceiro.model.grupo.dto.MovimentoGrupoDto;
import br.com.spendwise.controlefinanceiro.model.grupo.dto.ListagemGrupoDto;
import br.com.spendwise.controlefinanceiro.service.GrupoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grupos")
@AllArgsConstructor
public class GrupoController {

    private final GrupoService service;

    @GetMapping
    public ResponseEntity<List<ListagemGrupoDto>> listar(@RequestParam(name = "usuario") String usuario) {
        List<ListagemGrupoDto> listagem = service.listar(usuario);
        return ResponseEntity.status(HttpStatus.OK).body(listagem);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesGrupoDto> detalhes(@PathVariable("id") String id) {
        DetalhesGrupoDto detalhes = service.detalhar(id);
        return ResponseEntity.status(HttpStatus.OK).body(detalhes);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid CadastroGrupoDto cadastroGrupoDto) {
        service.cadastrar(cadastroGrupoDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Transactional
    @PostMapping("/entrar")
    public ResponseEntity<?> entrarGrupo(@RequestBody @Valid MovimentoGrupoDto movimentoGrupoDto) {
        service.adicionarIntegrante(movimentoGrupoDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
