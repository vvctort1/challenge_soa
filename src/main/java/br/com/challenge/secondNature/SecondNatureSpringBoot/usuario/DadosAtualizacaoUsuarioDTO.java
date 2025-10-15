package br.com.challenge.secondNature.SecondNatureSpringBoot.usuario;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(description = "Dados para atualização de usuário existente")
public record DadosAtualizacaoUsuarioDTO(

        @NotNull(message = "ID do usuário é obrigatório")
        @Schema(description = "ID do usuário a ser atualizado", example = "1", required = true)
        Long id_usuario,

        @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
        @Schema(description = "Novo nome (opcional)", example = "João Pedro Silva")
        String nome,

        @Email(message = "Email inválido")
        @Schema(description = "Novo email (opcional)", example = "joao.pedro@email.com")
        String email,

        @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
        @Schema(description = "Nova senha (opcional)", example = "novaSenha123")
        String senha

) {
}