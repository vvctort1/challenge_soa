package br.com.challenge.secondNature.SecondNatureSpringBoot.usuario;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados públicos de um usuário")
public record DadosListagemUsuarioDTO(

        @Schema(description = "ID único do usuário", example = "1")
        Long id_usuario,

        @Schema(description = "Nome do usuário", example = "João Silva")
        String nome,

        @Schema(description = "Email do usuário", example = "joao.silva@email.com")
        String email

) {

    public DadosListagemUsuarioDTO(Usuario dados){
        this(dados.getId_usuario(), dados.getNome(), dados.getEmail());
    }
}