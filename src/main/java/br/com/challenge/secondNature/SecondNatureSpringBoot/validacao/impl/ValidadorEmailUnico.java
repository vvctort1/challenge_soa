package br.com.challenge.secondNature.SecondNatureSpringBoot.validacao.impl;

import br.com.challenge.secondNature.SecondNatureSpringBoot.repository.UsuarioRepository;
import br.com.challenge.secondNature.SecondNatureSpringBoot.usuario.Usuario;
import br.com.challenge.secondNature.SecondNatureSpringBoot.validacao.ValidadorEntidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorEmailUnico implements ValidadorEntidade<Usuario> {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public void validar(Usuario usuario) {
        if (!podeExecutar(usuario)){
            throw new IllegalStateException("Email já cadastrado no sistema");
        }
    }

    @Override
    public boolean podeExecutar(Usuario usuario) {
        if (usuario.getEmail() == null || usuario.getEmail().isEmpty()){
            return false;
        }

        var usuarioExistente = repository.findByEmail(usuario.getEmail());

        if (usuarioExistente.isEmpty()){
            return true;
        }

        return usuarioExistente.get().getId_usuario().equals(usuario.getId_usuario());
    }
}
