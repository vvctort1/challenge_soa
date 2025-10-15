package br.com.challenge.secondNature.SecondNatureSpringBoot.service;


import br.com.challenge.secondNature.SecondNatureSpringBoot.repository.UsuarioRepository;
import br.com.challenge.secondNature.SecondNatureSpringBoot.usuario.*;
import br.com.challenge.secondNature.SecondNatureSpringBoot.validacao.ValidadorEntidade;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private List<ValidadorEntidade<Usuario>> validadores;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public Usuario cadastrarUsuario(DadosCadastroUsuarioDTO dados) {
        var usuario = new Usuario(dados);

        // Criptografa a senha antes de salvar
        usuario.setSenha(passwordEncoder.encode(dados.senha()));

        // Polimorfismo: executa todos os validadores
        validadores.forEach(validador -> validador.validar(usuario));

        if (!usuario.validar()) {
            throw new IllegalStateException(usuario.mensagemErro());
        }

        return repository.save(usuario);
    }


    public Page<Usuario> listarUsuariosAtivos(Pageable paginacao){
        return repository.findAllByAtivoTrue(paginacao);
    }


    public Usuario detalharUsuario(Long id) {
        var usuario = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        if (!usuario.getAtivo()) {
            throw new EntityNotFoundException("Usuário inativo");
        }
        return usuario;
    }


    @Transactional
    public Usuario atualizarUsuario(DadosAtualizacaoUsuarioDTO dados) {
        Usuario usuario = repository.findById(dados.id_usuario()).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        usuario.atualizarInformacoes(dados);

        if (!usuario.validar()) {
            throw new IllegalStateException(usuario.mensagemErro());
        }

        return usuario;
    }


    @Transactional
    public void excluirUsuario(Long id) {
        Usuario usuario = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        usuario.excluir();
    }


    public void validarUsuarioAtivo(Long id) {
        var usuario = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        if (!usuario.getAtivo()){
            throw new IllegalStateException("Usuário inativo");
        }
    }

}
