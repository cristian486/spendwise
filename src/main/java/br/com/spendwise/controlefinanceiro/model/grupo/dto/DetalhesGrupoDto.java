package br.com.spendwise.controlefinanceiro.model.grupo.dto;

import br.com.spendwise.controlefinanceiro.model.grupo.Grupo;

public record DetalhesGrupoDto(String id, String nome, String dono, String donoId) {

    public DetalhesGrupoDto(Grupo grupo) {
        this(grupo.getId(), grupo.getNome(), grupo.getDono(), grupo.getDonoId());
    }
}
