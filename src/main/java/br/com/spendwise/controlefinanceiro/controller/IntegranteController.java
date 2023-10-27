package br.com.spendwise.controlefinanceiro.controller;

import br.com.spendwise.controlefinanceiro.model.grupo.dto.MovimentoGrupoDto;
import br.com.spendwise.controlefinanceiro.model.integrante.dto.ListagemIntegranteDto;
import br.com.spendwise.controlefinanceiro.service.IntegranteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/integrantes")
@AllArgsConstructor
public class IntegranteController {

    private final IntegranteService service;


    @GetMapping
    public ResponseEntity<List<ListagemIntegranteDto>> listar(@RequestParam(name = "grupoId") String grupoId) {
        List<ListagemIntegranteDto> integrantes = service.listar(grupoId);
        return ResponseEntity.status(HttpStatus.OK).body(integrantes);
    }

    @Transactional
    @DeleteMapping
    public ResponseEntity<?> deletar(@RequestBody @Valid MovimentoGrupoDto movimentacaoGrupo) {
        service.deletar(movimentacaoGrupo.usuario(), movimentacaoGrupo.grupoId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
