package br.com.challenge.secondNature.SecondNatureSpringBoot.controller;


import br.com.challenge.secondNature.SecondNatureSpringBoot.acesso.Acesso;
import br.com.challenge.secondNature.SecondNatureSpringBoot.acesso.AcessoRepository;
import br.com.challenge.secondNature.SecondNatureSpringBoot.acesso.DadosCadastroAcessoDTO;
import br.com.challenge.secondNature.SecondNatureSpringBoot.acesso.DadosListagemAcessoDTO;
import br.com.challenge.secondNature.SecondNatureSpringBoot.usuario.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/acesso")
public class AcessoController {

    @Autowired
    AcessoRepository repository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosListagemAcessoDTO> cadastrarAcesso(@RequestBody @Valid DadosCadastroAcessoDTO dados){
        try{
            var usuario = usuarioRepository.getReferenceById(dados.id_usuario());
            if (!usuario.getAtivo()){
                return ResponseEntity.badRequest().build();
            }
        } catch(Exception e) {
            return ResponseEntity.badRequest().build();
        }

        var acesso = new Acesso(dados);
        repository.save(acesso);

        return ResponseEntity.ok(new DadosListagemAcessoDTO(acesso));
    }



    @GetMapping
    public ResponseEntity<Page<DadosListagemAcessoDTO>> listarAcessos(
            @PageableDefault(size = 10, sort = {"data"}) Pageable paginacao){
        try {
            System.out.println("Iniciando busca de acessos...");
            var page = repository.findAll(paginacao).map(DadosListagemAcessoDTO::new);
            System.out.println("Busca concluída. Total: " + page.getTotalElements());
            return ResponseEntity.ok(page);
        } catch (Exception e) {
            System.err.println("Erro ao buscar acessos: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/usuario/{id_usuario}")
    public ResponseEntity<Page<DadosListagemAcessoDTO>> listarPorUsuario(
            @PathVariable Long id_usuario,
            @PageableDefault(size = 10) Pageable paginacao){
        try {
            var page = repository.findByIdUsuarioOrderByDataAcessoDesc(id_usuario, paginacao)
                    .map(DadosListagemAcessoDTO::new);
            return ResponseEntity.ok(page);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}