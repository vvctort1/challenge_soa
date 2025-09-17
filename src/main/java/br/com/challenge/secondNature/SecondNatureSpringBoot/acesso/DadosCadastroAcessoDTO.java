package br.com.challenge.secondNature.SecondNatureSpringBoot.acesso;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosCadastroAcessoDTO(

        @NotNull
        Long id_usuario,

        LocalDateTime data

) {
}
