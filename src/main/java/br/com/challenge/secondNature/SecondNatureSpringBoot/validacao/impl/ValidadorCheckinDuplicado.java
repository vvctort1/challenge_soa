package br.com.challenge.secondNature.SecondNatureSpringBoot.validacao.impl;

import br.com.challenge.secondNature.SecondNatureSpringBoot.checkin.Checkin;
import br.com.challenge.secondNature.SecondNatureSpringBoot.repository.CheckinRepository;
import br.com.challenge.secondNature.SecondNatureSpringBoot.validacao.ValidadorEntidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorCheckinDuplicado implements ValidadorEntidade<Checkin> {

    @Autowired
    private CheckinRepository repository;

    @Override
    public void validar(Checkin checkin) {
        if (!podeExecutar(checkin)){
            throw new IllegalStateException("Usuário já realizou o check-in hoje.");
        }
    }

    @Override
    public boolean podeExecutar(Checkin checkin) {
        return !repository.jaFezCheckinHoje(checkin.getId_usuario());
    }
}
