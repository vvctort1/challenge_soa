package br.com.challenge.secondNature.SecondNatureSpringBoot.usuario;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoUsuarioDTO(

        @NotNull
        Long id_usuario,
        String nome,
        String email,
        String senha

) {
}
