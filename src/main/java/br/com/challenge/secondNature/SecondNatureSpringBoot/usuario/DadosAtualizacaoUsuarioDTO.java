package br.com.challenge.secondNature.SecondNatureSpringBoot.usuario;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoUsuarioDTO(
        String nome,
        String email,
        String senha
) {
}
