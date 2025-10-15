package br.com.challenge.secondNature.SecondNatureSpringBoot.auth;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Token JWT de autenticação")
public record DadosTokenDTO(

        @Schema(description = "Token JWT para autenticação",
                example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
        String token,

        @Schema(description = "Tipo do token", example = "Bearer")
        String tipo,

        @Schema(description = "ID do usuário autenticado", example = "1")
        Long usuarioId,

        @Schema(description = "Nome do usuário autenticado", example = "João Silva")
        String nome,

        @Schema(description = "Email do usuário autenticado", example = "joao@email.com")
        String email

) {
}