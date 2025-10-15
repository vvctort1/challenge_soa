package br.com.challenge.secondNature.SecondNatureSpringBoot.validacao.impl;

import br.com.challenge.secondNature.SecondNatureSpringBoot.usuario.Usuario;
import br.com.challenge.secondNature.SecondNatureSpringBoot.validacao.ValidadorEntidade;
import org.springframework.stereotype.Component;

@Component
public class ValidadorUsuarioAtivo implements ValidadorEntidade<Usuario> {

    @Override
    public void validar(Usuario usuario) {
        if(!podeExecutar(usuario)){
            throw new IllegalStateException("Usuário inativo ou inválido");
        }
    }

    @Override
    public boolean podeExecutar(Usuario usuario) {
        return usuario != null && usuario.getAtivo();
    }
}
