package br.com.challenge.secondNature.SecondNatureSpringBoot.checkin;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record DadosListagemCheckinDTO(

        Long id_checkin,
        Long id_usuario,
        String humor,
        Impulsividade impulsividade_nivel,
        @JsonFormat (pattern="dd/MM/yyyy HH:mm:ss")
        LocalDateTime data_checkin

) {

    public DadosListagemCheckinDTO(Checkin checkin){
        this(checkin.getId_checkin(), checkin.getId_usuario(), checkin.getHumor(),checkin.getImpulsividade_nivel(),checkin.getData_checkin());
    }
}
