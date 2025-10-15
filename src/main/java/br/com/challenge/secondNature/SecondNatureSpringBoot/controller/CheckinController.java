package br.com.challenge.secondNature.SecondNatureSpringBoot.controller;

import br.com.challenge.secondNature.SecondNatureSpringBoot.checkin.DadosCadastroCheckinDTO;
import br.com.challenge.secondNature.SecondNatureSpringBoot.checkin.DadosListagemCheckinDTO;
import br.com.challenge.secondNature.SecondNatureSpringBoot.service.CheckinService;
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
@RequestMapping("/checkin")
@Tag(name = "3. Check-ins", description = "Registro e consulta de check-ins diários de humor")
public class CheckinController {

    @Autowired
    private CheckinService service;

    @PostMapping
    @Operation(
            summary = "Registrar check-in diário",
            description = "Registra check-in com humor e nível de impulsividade. Limite de 1 check-in por dia por usuário."
    )
    public ResponseEntity<DadosListagemCheckinDTO> registrar(
            @RequestBody @Valid DadosCadastroCheckinDTO dados) {

        try {
            var checkin = service.cadastrarCheckin(dados);
            return ResponseEntity.ok(new DadosListagemCheckinDTO(checkin));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    @Operation(
            summary = "Listar todos os check-ins",
            description = "Retorna lista paginada de todos os check-ins registrados"
    )
    public ResponseEntity<Page<DadosListagemCheckinDTO>> listar(
            @Parameter(description = "Número da página")
            @RequestParam(defaultValue = "0") int page,

            @Parameter(description = "Registros por página")
            @RequestParam(defaultValue = "15") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "data"));
        var pagina = service.listarCheckins(pageable).map(DadosListagemCheckinDTO::new);

        return ResponseEntity.ok(pagina);
    }

    @GetMapping("/usuario/{idUsuario}")
    @Operation(
            summary = "Listar check-ins por usuário",
            description = "Retorna o histórico completo de check-ins de um usuário"
    )
    public ResponseEntity<Page<DadosListagemCheckinDTO>> listarPorUsuario(
            @Parameter(description = "ID do usuário", required = true)
            @PathVariable Long idUsuario,

            @Parameter(description = "Número da página")
            @RequestParam(defaultValue = "0") int page,

            @Parameter(description = "Registros por página")
            @RequestParam(defaultValue = "15") int size) {

        Pageable pageable = PageRequest.of(page, size);
        var pagina = service.listarCheckinPorUsuario(idUsuario, pageable)
                .map(DadosListagemCheckinDTO::new);

        return ResponseEntity.ok(pagina);
    }

    @GetMapping("/hoje/{idUsuario}")
    @Operation(
            summary = "Buscar check-in de hoje",
            description = "Retorna o check-in do dia atual do usuário, se existir"
    )
    public ResponseEntity<DadosListagemCheckinDTO> buscarHoje(
            @Parameter(description = "ID do usuário", required = true)
            @PathVariable Long idUsuario) {

        return service.buscarCheckinHoje(idUsuario)
                .map(checkin -> ResponseEntity.ok(new DadosListagemCheckinDTO(checkin)))
                .orElse(ResponseEntity.notFound().build());
    }
}