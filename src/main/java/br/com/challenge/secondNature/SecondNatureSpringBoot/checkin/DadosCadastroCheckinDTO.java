package br.com.challenge.secondNature.SecondNatureSpringBoot.checkin;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosCadastroCheckinDTO(

        @NotNull
        Long id_usuario,

        @NotBlank
        String humor,

        LocalDateTime data,

        @NotNull
        Impulsividade impulsividade_nivel
) {
}
