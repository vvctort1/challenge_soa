package br.com.challenge.secondNature.SecondNatureSpringBoot.acesso;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Dados para registrar um novo acesso")
public record DadosCadastroAcessoDTO(

        @NotNull(message = "ID do usuário é obrigatório")
        @Schema(description = "ID do usuário que está acessando", example = "1", required = true)
        Long id_usuario


) {
}