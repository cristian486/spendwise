package br.com.spendwise.controlefinanceiro.model.grupo.dto;

import jakarta.validation.constraints.NotBlank;

public record MovimentoGrupoDto(@NotBlank(message = "Obrigatório o envio do usuário")
                             String usuario,
                                @NotBlank(message = "Obrigatório o envio do ID do grupo")
                             String grupoId) {
}
