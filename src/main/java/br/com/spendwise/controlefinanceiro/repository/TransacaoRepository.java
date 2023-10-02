package br.com.spendwise.controlefinanceiro.repository;

import br.com.spendwise.controlefinanceiro.model.transacao.Transacao;
import br.com.spendwise.controlefinanceiro.model.transacao.dto.OverviewTransacaoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, String> {

    Page<Transacao> findAllByUsuario(Pageable pageable, String usuario);

    @Query("select t from Transacao t where t.usuario = :usuario and t.data >= :dataInicial and t.data <= :dataFinal")
    List<Transacao> visaoGeralDasTransacoesDoUsuario(@Param("usuario") String usuario, @Param("dataInicial") LocalDate dataInicial,
                                          @Param("dataFinal") LocalDate dataFinal);
}
