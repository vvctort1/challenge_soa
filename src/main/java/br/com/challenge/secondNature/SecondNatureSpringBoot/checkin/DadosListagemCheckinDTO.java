package br.com.challenge.secondNature.SecondNatureSpringBoot.checkin;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Dados de um check-in registrado")
public record DadosListagemCheckinDTO(

        @Schema(description = "ID único do check-in", example = "1")
        Long id_checkin,

        @Schema(description = "ID do usuário", example = "1")
        Long id_usuario,

        @Schema(description = "Descrição do humor", example = "Estou me sentindo bem hoje")
        String humor,

        @Schema(description = "Nível de impulsividade", example = "CONTROLADO")
        Impulsividade impulsividade_nivel,

        @Schema(description = "Data e hora do check-in", example = "14/10/2025 10:30:45")
        @JsonFormat(pattern="dd/MM/yyyy HH:mm:ss")
        LocalDateTime data

) {

    public DadosListagemCheckinDTO(Checkin checkin){
        this(checkin.getId_checkin(), checkin.getId_usuario(), checkin.getHumor(),
                checkin.getImpulsividade_nivel(), checkin.getData());
    }
}