package br.com.challenge.secondNature.SecondNatureSpringBoot.acesso;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record DadosListagemAcessoDTO(

        Long id_acesso,
        Long id_usuario,
        @JsonFormat(pattern="dd/MM/yyyy HH:mm:ss")
        LocalDateTime data

) {
    public DadosListagemAcessoDTO(Acesso acesso){
        this(acesso.getId_acesso(), acesso.getId_usuario(), acesso.getData());
    }
}
