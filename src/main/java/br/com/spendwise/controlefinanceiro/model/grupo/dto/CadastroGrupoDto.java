package br.com.spendwise.controlefinanceiro.model.grupo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CadastroGrupoDto(@Size(max = 255)
                               @NotBlank(message = "Obrigatório o envio do nome do grupo")
                               String nome,
                               @NotBlank(message = "O envio do nome do dono do grupo é obrigatório")
                               String dono,
                               @NotBlank(message = "Obrigatório o envio do usuário")
                               String usuario) {
}
