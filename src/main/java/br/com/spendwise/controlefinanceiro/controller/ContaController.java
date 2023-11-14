package br.com.spendwise.controlefinanceiro.controller;

import br.com.spendwise.controlefinanceiro.model.transacao.dto.OverviewTransacaoDto;
import br.com.spendwise.controlefinanceiro.service.ContaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contas")
@AllArgsConstructor
public class ContaController {

    private final ContaService service;

    @GetMapping("/overview")
    public ResponseEntity<OverviewTransacaoDto> overview(@RequestParam(name = "usuario") String usuario) {
        OverviewTransacaoDto overview = service.visaoGeral(usuario);
        return ResponseEntity.status(HttpStatus.OK).body(overview);
    }
}
