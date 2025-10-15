package br.com.challenge.secondNature.SecondNatureSpringBoot.controller;

import br.com.challenge.secondNature.SecondNatureSpringBoot.auth.DadosLoginDTO;
import br.com.challenge.secondNature.SecondNatureSpringBoot.auth.DadosTokenDTO;
import br.com.challenge.secondNature.SecondNatureSpringBoot.service.TokenService;
import br.com.challenge.secondNature.SecondNatureSpringBoot.usuario.Usuario;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticação", description = "Endpoints para autenticação e geração de tokens JWT")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    @Operation(
            summary = "Realizar login",
            description = "Autentica o usuário e retorna um token JWT válido por 24 horas"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login realizado com sucesso",
                    content = @Content(schema = @Schema(implementation = DadosTokenDTO.class))),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas",
                    content = @Content)
    })
    public ResponseEntity<?> login(@RequestBody @Valid DadosLoginDTO dados) {
        try {
            var authenticationToken = new UsernamePasswordAuthenticationToken(
                    dados.email(),
                    dados.senha()
            );

            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            Usuario usuario = (Usuario) authentication.getPrincipal();

            String token = tokenService.gerarToken(usuario);

            var tokenDTO = new DadosTokenDTO(
                    token,
                    "Bearer",
                    usuario.getId_usuario(),
                    usuario.getNome(),
                    usuario.getEmail()
            );

            return ResponseEntity.ok(tokenDTO);

        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Email ou senha inválidos");
        }
    }
}