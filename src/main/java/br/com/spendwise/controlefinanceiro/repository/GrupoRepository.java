package br.com.spendwise.controlefinanceiro.repository;

import br.com.spendwise.controlefinanceiro.model.grupo.Grupo;
import br.com.spendwise.controlefinanceiro.model.integrante.Integrante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, String> {
    List<Grupo> findAllByIntegrantesContains(Integrante integrante);
}
