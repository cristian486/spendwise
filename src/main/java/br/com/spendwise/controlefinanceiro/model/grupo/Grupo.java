package br.com.spendwise.controlefinanceiro.model.grupo;

import br.com.spendwise.controlefinanceiro.model.integrante.Integrante;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Grupo {

    @Id
    @UuidGenerator
    private String id;
    private String nome;
    private String dono;
    private String donoId;
    @OneToMany(mappedBy = "grupo")
    List<Integrante> integrantes = new ArrayList<>();

    public Grupo(String nome, String dono, String donoId) {
        this.nome = nome;
        this.dono = dono;
        this.donoId = donoId;
    }

    public void adicionarIntegrante(Integrante integrante) {
        this.integrantes.add(integrante);
    }
}
