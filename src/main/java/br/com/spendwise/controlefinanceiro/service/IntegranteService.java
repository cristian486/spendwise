package br.com.spendwise.controlefinanceiro.service;

import br.com.spendwise.controlefinanceiro.model.grupo.Grupo;
import br.com.spendwise.controlefinanceiro.model.integrante.Integrante;
import br.com.spendwise.controlefinanceiro.model.integrante.dto.ListagemIntegranteDto;
import br.com.spendwise.controlefinanceiro.repository.IntegranteRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class IntegranteService {

    private final Keycloak keycloak;
    private final IntegranteRepository repository;

    Optional<Integrante> findByUsuario(String usuario) {
        return repository.findByUsuarioLike("%" + usuario + "%");
    }

    Integrante findById(String id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Integrante requisitado não foi encontrado"));
    }

    public void deletar(String id, String grupoId) {
        Integrante integrante = repository.findByUsuarioAndGrupoId(id, grupoId);
        repository.delete(integrante);
    }

    public Integrante cadastrar(Grupo grupo, String usuario) {
        Integrante integrante = new Integrante(null, grupo, usuario);
        repository.save(integrante);
        return integrante;
    }

    public List<ListagemIntegranteDto> listar(String grupoId) {
        List<ListagemIntegranteDto> result = new ArrayList<>();
        repository.findByGrupo_Id(grupoId).ifPresent(integrantes -> {
            integrantes.forEach(i -> {
                UserResource user = keycloak.realm("spendwise").users().get(i.getUsuario());
                result.add(new ListagemIntegranteDto(i.getUsuario(), user.toRepresentation().getFirstName() + " " + user.toRepresentation().getLastName()));
            });
        });
        return result;
    }
}
