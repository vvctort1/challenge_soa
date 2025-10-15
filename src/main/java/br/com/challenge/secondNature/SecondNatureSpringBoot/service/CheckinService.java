package br.com.challenge.secondNature.SecondNatureSpringBoot.service;
import br.com.challenge.secondNature.SecondNatureSpringBoot.checkin.Checkin;
import br.com.challenge.secondNature.SecondNatureSpringBoot.checkin.DadosCadastroCheckinDTO;
import br.com.challenge.secondNature.SecondNatureSpringBoot.repository.CheckinRepository;
import br.com.challenge.secondNature.SecondNatureSpringBoot.validacao.ValidadorEntidade;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CheckinService {

    @Autowired
    private CheckinRepository checkinRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private List<ValidadorEntidade<Checkin>> validadores;

    @Transactional
    public Checkin cadastrarCheckin(DadosCadastroCheckinDTO dados){
        usuarioService.validarUsuarioAtivo(dados.id_usuario());

        var checkin = new Checkin(dados);

        validadores.forEach(validador -> validador.validar(checkin));

        if (!checkin.validar()) {
            throw new IllegalStateException(checkin.mensagemErro());
        }

        return checkinRepository.save(checkin);
    }


    public Page<Checkin> listarCheckins(Pageable paginacao) {
        return checkinRepository.findAll(paginacao);
    }

    public Page<Checkin> listarCheckinPorUsuario(Long idUsuario, Pageable paginacao) {
        return checkinRepository.findByIdUsuarioOrderByDataCheckinDesc(idUsuario, paginacao);
    }

    public Optional<Checkin> buscarCheckinHoje(Long idUsuario) {
        return checkinRepository.findCheckinHoje(idUsuario);
    }

}
