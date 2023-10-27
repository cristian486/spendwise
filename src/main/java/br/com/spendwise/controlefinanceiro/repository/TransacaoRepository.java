package br.com.spendwise.controlefinanceiro.repository;

import br.com.spendwise.controlefinanceiro.model.transacao.TipoTransacao;
import br.com.spendwise.controlefinanceiro.model.transacao.Transacao;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, String> {

    Page<Transacao> findAllByUsuario(Pageable pageable, String usuario);

    List<Transacao> findAllByUsuarioLikeIgnoreCase(String usuario);

    @Cacheable(cacheNames = "gaficoTransacoes", key = "#usuario")
    @Query("select t.categoria as categoria, sum(t.valor) as valor from Transacao t where t.usuario = :usuario and t.tipo = :tipo group by t.categoria")
    List<Map<String, Object>> dadosGraficoTransacoes(@Param("usuario") String usuario, TipoTransacao tipo);
}
