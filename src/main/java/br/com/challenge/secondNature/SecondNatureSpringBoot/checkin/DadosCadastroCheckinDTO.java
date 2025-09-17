package br.com.challenge.secondNature.SecondNatureSpringBoot.checkin;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroCheckinDTO(

        @NotNull
        Long id_usuario,

        @NotBlank
        String humor,

        @NotNull
        Impulsividade impulsividade_nivel
) {
}
