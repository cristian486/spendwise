package br.com.spendwise.controlefinanceiro.controller;

import br.com.spendwise.controlefinanceiro.model.graficos.DadosGraficoDebitosDto;
import br.com.spendwise.controlefinanceiro.model.transacao.TipoTransacao;
import br.com.spendwise.controlefinanceiro.service.GraficoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/graficos")
@AllArgsConstructor
public class GraficoController {

    private final GraficoService service;


    @GetMapping("/debito")
    public ResponseEntity<List<DadosGraficoDebitosDto>> debito(@RequestParam(name = "usuario") String usuario) {
        List<DadosGraficoDebitosDto> dadosGrafico = service.graficoDasTransacoes(usuario, TipoTransacao.DEBITO);
        return ResponseEntity.status(HttpStatus.OK).body(dadosGrafico);
    }

}
