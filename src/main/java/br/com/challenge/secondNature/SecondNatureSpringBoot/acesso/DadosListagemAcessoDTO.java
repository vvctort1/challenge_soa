package br.com.challenge.secondNature.SecondNatureSpringBoot.acesso;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Dados de um acesso registrado")
public record DadosListagemAcessoDTO(

        @Schema(description = "ID único do acesso", example = "1")
        Long id_acesso,

        @Schema(description = "ID do usuário", example = "1")
        Long id_usuario,

        @Schema(description = "Data e hora do acesso", example = "14/10/2025 10:30:45")
        @JsonFormat(pattern="dd/MM/yyyy HH:mm:ss")
        LocalDateTime data

) {
    public DadosListagemAcessoDTO(Acesso acesso){
        this(acesso.getId_acesso(), acesso.getId_usuario(), acesso.getData());
    }
}