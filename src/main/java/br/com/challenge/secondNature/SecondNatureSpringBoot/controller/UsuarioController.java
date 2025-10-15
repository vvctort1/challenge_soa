package br.com.challenge.secondNature.SecondNatureSpringBoot.controller;

import br.com.challenge.secondNature.SecondNatureSpringBoot.service.UsuarioService;
import br.com.challenge.secondNature.SecondNatureSpringBoot.usuario.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuario")
@Tag(name = "1. Usuários", description = "Gerenciamento completo de usuários do sistema")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping
    @Operation(
            summary = "Criar novo usuário",
            description = "Cadastra um novo usuário no sistema. Email deve ser único."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso",
                    content = @Content(schema = @Schema(implementation = DadosListagemUsuarioDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos ou email já cadastrado",
                    content = @Content)
    })
    public ResponseEntity<DadosListagemUsuarioDTO> cadastrar(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados do novo usuário",
                    required = true
            )
            @RequestBody @Valid DadosCadastroUsuarioDTO dados,
            UriComponentsBuilder uriBuilder) {

        var usuario = service.cadastrarUsuario(dados);
        var uri = uriBuilder.path("/usuario/{id}")
                .buildAndExpand(usuario.getId_usuario()).toUri();

        return ResponseEntity.created(uri).body(new DadosListagemUsuarioDTO(usuario));
    }

    @GetMapping
    @Operation(
            summary = "Listar usuários ativos",
            description = "Retorna lista paginada de todos os usuários ativos do sistema"
    )
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    public ResponseEntity<Page<DadosListagemUsuarioDTO>> listar(
            @Parameter(description = "Número da página (começa em 0)")
            @RequestParam(defaultValue = "0") int page,

            @Parameter(description = "Quantidade de registros por página")
            @RequestParam(defaultValue = "10") int size,

            @Parameter(description = "Campo para ordenação (nome, email)")
            @RequestParam(defaultValue = "nome") String sortBy,

            @Parameter(description = "Direção da ordenação")
            @RequestParam(defaultValue = "ASC") String direction) {

        Sort.Direction sortDirection = "DESC".equalsIgnoreCase(direction)
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));
        var pagina = service.listarUsuariosAtivos(pageable)
                .map(DadosListagemUsuarioDTO::new);

        return ResponseEntity.ok(pagina);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar usuário por ID",
            description = "Retorna os dados de um usuário específico"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public ResponseEntity<DadosListagemUsuarioDTO> buscarPorId(
            @Parameter(description = "ID do usuário", required = true)
            @PathVariable Long id) {

        try {
            var usuario = service.detalharUsuario(id);
            return ResponseEntity.ok(new DadosListagemUsuarioDTO(usuario));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    @Operation(
            summary = "Atualizar usuário",
            description = "Atualiza os dados de um usuário existente. Campos null não são atualizados."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário atualizado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<DadosListagemUsuarioDTO> atualizar(
            @RequestBody @Valid DadosAtualizacaoUsuarioDTO dados) {

        try {
            var usuario = service.atualizarUsuario(dados);
            return ResponseEntity.ok(new DadosListagemUsuarioDTO(usuario));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Excluir usuário",
            description = "Realiza exclusão lógica do usuário (marca como inativo)"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Usuário excluído"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public ResponseEntity<Void> excluir(
            @Parameter(description = "ID do usuário a excluir", required = true)
            @PathVariable Long id) {

        try {
            service.excluirUsuario(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
