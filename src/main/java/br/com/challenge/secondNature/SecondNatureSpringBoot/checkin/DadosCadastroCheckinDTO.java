package br.com.challenge.secondNature.SecondNatureSpringBoot.checkin;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Dados para registrar um check-in diário")
public record DadosCadastroCheckinDTO(

        @NotNull(message = "ID do usuário é obrigatório")
        @Schema(description = "ID do usuário", example = "1", required = true)
        Long id_usuario,

        @NotBlank(message = "Descrição do humor é obrigatória")
        @Schema(description = "Descrição detalhada do humor/estado emocional",
                example = "Estou me sentindo muito bem hoje! Acordei animado para trabalhar.",
                required = true)
        String humor,

        @NotNull(message = "Nível de impulsividade é obrigatório")
        @Schema(description = "Nível de impulsividade do usuário",
                example = "CONTROLADO",
                allowableValues = {"CONTROLADO", "MODERADO", "ALTO"},
                required = true)
        Impulsividade impulsividade_nivel
) {
}
