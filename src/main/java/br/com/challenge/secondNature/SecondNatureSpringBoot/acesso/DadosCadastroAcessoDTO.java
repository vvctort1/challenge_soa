package br.com.challenge.secondNature.SecondNatureSpringBoot.acesso;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosCadastroAcessoDTO(

        LocalDateTime data

) {
}
