package br.com.challenge.secondNature.SecondNatureSpringBoot.controller;


import br.com.challenge.secondNature.SecondNatureSpringBoot.usuario.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosListagemUsuarioDTO> cadastrarUsuario(
            @RequestBody @Valid DadosCadastroUsuarioDTO dados,
            UriComponentsBuilder uriBuilder) {

        var usuario = new Usuario(dados);
        repository.save(usuario);

        var uri = uriBuilder.path("/usuario/{id}")
                .buildAndExpand(usuario.getId_usuario()).toUri();

        return ResponseEntity.created(uri).body(new DadosListagemUsuarioDTO(usuario));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemUsuarioDTO>> listarUsuarios(
            @PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {

        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemUsuarioDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosListagemUsuarioDTO> detalharUsuario(@PathVariable Long id) {
        try {
            var usuario = repository.getReferenceById(id);

            if (!usuario.getAtivo()) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(new DadosListagemUsuarioDTO(usuario));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id_usuario}")
    @Transactional
    public ResponseEntity<DadosListagemUsuarioDTO> atualizarUsuario(
            @PathVariable Long id_usuario,
            @RequestBody @Valid DadosAtualizacaoUsuarioDTO dados) {

        return repository.findById(id_usuario)
                .map(usuario -> {
                    usuario.atualizarInformacoes(dados);
                    return ResponseEntity.ok(new DadosListagemUsuarioDTO(usuario));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id_usuario}")
    @Transactional
    public ResponseEntity<Void> excluirUsuario(@PathVariable Long id_usuario) {
        try {
            Usuario usuario = repository.getReferenceById(id_usuario);
            usuario.excluir();

            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}