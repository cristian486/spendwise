package br.com.spendwise.controlefinanceiro.service;

import br.com.spendwise.controlefinanceiro.model.graficos.DadosGraficoDebitosDto;
import br.com.spendwise.controlefinanceiro.model.transacao.Categoria;
import br.com.spendwise.controlefinanceiro.model.transacao.TipoTransacao;
import br.com.spendwise.controlefinanceiro.repository.TransacaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class GraficoService {

    private final TransacaoRepository repository;

    public List<DadosGraficoDebitosDto> graficoDasTransacoes(String usuario, TipoTransacao tipo) {
        List<DadosGraficoDebitosDto> result = new LinkedList<>();
        List<Map<String, Object>> resultado = repository.dadosGraficoTransacoes(usuario, tipo);
        resultado.forEach(map -> result.add(new DadosGraficoDebitosDto((Categoria) map.get("categoria"),  (BigDecimal) map.get("valor"))));
        return result;
    }
}
