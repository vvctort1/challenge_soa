package br.com.challenge.secondNature.SecondNatureSpringBoot.acesso;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroAcessoDTO(

        @NotNull
        Long id_usuario

) {
}
