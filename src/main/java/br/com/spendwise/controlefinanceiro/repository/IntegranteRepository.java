package br.com.spendwise.controlefinanceiro.repository;

import br.com.spendwise.controlefinanceiro.model.integrante.Integrante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IntegranteRepository extends JpaRepository<Integrante, String> {

    Optional<Integrante> findByUsuarioLike(String usuario);

    Optional<List<Integrante>> findByGrupo_Id(String id);

    @Query("select i from Integrante i where i.usuario = :usuario and i.grupo.id = :id")
    Integrante findByUsuarioAndGrupoId(@Param("usuario") String usuario, @Param("id") String grupoId);
}
