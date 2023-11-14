package br.com.spendwise.controlefinanceiro.repository;

import br.com.spendwise.controlefinanceiro.model.conta.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContaRepository extends JpaRepository<Conta, String> {

    Optional<Conta> findByUsuario(String usuario);
}
