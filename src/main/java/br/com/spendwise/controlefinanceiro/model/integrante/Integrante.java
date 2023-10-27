package br.com.spendwise.controlefinanceiro.model.integrante;

import br.com.spendwise.controlefinanceiro.model.grupo.Grupo;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Integrante {

    @Id
    @UuidGenerator
    private String id;
    @ManyToOne
    private Grupo grupo;
    private String usuario;
}
