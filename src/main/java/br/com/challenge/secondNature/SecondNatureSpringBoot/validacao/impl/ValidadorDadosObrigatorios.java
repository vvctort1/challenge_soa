package br.com.challenge.secondNature.SecondNatureSpringBoot.validacao.impl;

import br.com.challenge.secondNature.SecondNatureSpringBoot.validacao.ValidadorEntidade;
import org.springframework.stereotype.Component;

@Component
public class ValidadorDadosObrigatorios<T> implements ValidadorEntidade<T> {

    @Override
    public void validar(T entidade) {
        if (!podeExecutar(entidade)) {
            throw new IllegalStateException("Dados obrigatórios não preenchidos");
        }
    }

    @Override
    public boolean podeExecutar(T entidade) {
        return entidade != null;
    }
}
