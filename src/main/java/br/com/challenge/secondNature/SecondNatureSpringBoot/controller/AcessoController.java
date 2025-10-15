package br.com.challenge.secondNature.SecondNatureSpringBoot.controller;

import br.com.challenge.secondNature.SecondNatureSpringBoot.acesso.DadosCadastroAcessoDTO;
import br.com.challenge.secondNature.SecondNatureSpringBoot.acesso.DadosListagemAcessoDTO;
import br.com.challenge.secondNature.SecondNatureSpringBoot.service.AcessoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/acesso")
@Tag(name = "2. Acessos", description = "Registro e consulta de acessos ao sistema")
public class AcessoController {

    @Autowired
    private AcessoService service;

    @PostMapping
    @Operation(
            summary = "Registrar acesso",
            description = "Registra um novo acesso do usuário ao sistema com timestamp automático"
    )
    public ResponseEntity<DadosListagemAcessoDTO> registrar(
            @RequestBody @Valid DadosCadastroAcessoDTO dados) {

        try {
            var acesso = service.cadastrarAcesso(dados);
            return ResponseEntity.ok(new DadosListagemAcessoDTO(acesso));
        } catch (EntityNotFoundException | IllegalStateException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    @Operation(
            summary = "Listar todos os acessos",
            description = "Retorna lista paginada de todos os acessos registrados, ordenados por data"
    )
    public ResponseEntity<Page<DadosListagemAcessoDTO>> listar(
            @Parameter(description = "Número da página")
            @RequestParam(defaultValue = "0") int page,

            @Parameter(description = "Registros por página")
            @RequestParam(defaultValue = "20") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "data"));
        var pagina = service.listarAcessos(pageable).map(DadosListagemAcessoDTO::new);

        return ResponseEntity.ok(pagina);
    }

    @GetMapping("/usuario/{idUsuario}")
    @Operation(
            summary = "Listar acessos por usuário",
            description = "Retorna o histórico de acessos de um usuário específico"
    )
    public ResponseEntity<Page<DadosListagemAcessoDTO>> listarPorUsuario(
            @Parameter(description = "ID do usuário", required = true)
            @PathVariable Long idUsuario,

            @Parameter(description = "Número da página")
            @RequestParam(defaultValue = "0") int page,

            @Parameter(description = "Registros por página")
            @RequestParam(defaultValue = "20") int size) {

        Pageable pageable = PageRequest.of(page, size);
        var pagina = service.listarAcessosPorUsuario(idUsuario, pageable)
                .map(DadosListagemAcessoDTO::new);

        return ResponseEntity.ok(pagina);
    }
}