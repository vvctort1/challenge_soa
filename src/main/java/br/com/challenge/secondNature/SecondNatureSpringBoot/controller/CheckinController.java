package br.com.challenge.secondNature.SecondNatureSpringBoot.controller;


import br.com.challenge.secondNature.SecondNatureSpringBoot.checkin.Checkin;
import br.com.challenge.secondNature.SecondNatureSpringBoot.checkin.CheckinRepository;
import br.com.challenge.secondNature.SecondNatureSpringBoot.checkin.DadosCadastroCheckinDTO;
import br.com.challenge.secondNature.SecondNatureSpringBoot.checkin.DadosListagemCheckinDTO;
import br.com.challenge.secondNature.SecondNatureSpringBoot.usuario.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/checkin")
public class CheckinController {

    @Autowired
    CheckinRepository repository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosListagemCheckinDTO> cadastrarCheckin(@RequestBody @Valid DadosCadastroCheckinDTO dados){
        try{
            var usuario = usuarioRepository.getReferenceById(dados.id_usuario());
            if (!usuario.getAtivo()){
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

        if (repository.jaFezCheckinHoje(dados.id_usuario())){
            return ResponseEntity.badRequest().build();
        }

        var checkin = new Checkin(dados);
        repository.save(checkin);

        return ResponseEntity.ok(new DadosListagemCheckinDTO(checkin));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemCheckinDTO>> listarCheckins(@PageableDefault(size = 10, sort = {"data"})Pageable paginacao){
        var page = repository.findAll(paginacao).map(DadosListagemCheckinDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/usuario/{id_usuario}")
    public ResponseEntity<Page<DadosListagemCheckinDTO>> listarPorUsuario(@PathVariable Long id_usuario, @PageableDefault(size = 10) Pageable paginacao){
        var page = repository.findByIdUsuarioOrderByDataCheckinDesc(id_usuario, paginacao).map(DadosListagemCheckinDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/hoje/{id_usuario}")
    public ResponseEntity<DadosListagemCheckinDTO> checkinHoje(@PathVariable Long id_usuario){
        var checkin = repository.findCheckinHoje(id_usuario);

        return checkin.map(value -> ResponseEntity.ok(new DadosListagemCheckinDTO(value))).orElseGet(() -> ResponseEntity.notFound().build());

    }
}
