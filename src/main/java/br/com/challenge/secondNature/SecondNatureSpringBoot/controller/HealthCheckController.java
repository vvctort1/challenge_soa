package br.com.challenge.secondNature.SecondNatureSpringBoot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/health_check")
@Tag(name = "0. Health Check", description = "Verificação de saúde da API")
public class HealthCheckController {

    @GetMapping
    @Operation(
            summary = "Verificar status da API",
            description = "Retorna informações sobre o status e disponibilidade da API"
    )
    public ResponseEntity<Map<String, Object>> healthCheck() {
        Map<String, Object> health = new HashMap<>();
        health.put("status", "UP");
        health.put("service", "Second Nature API");
        health.put("timestamp", LocalDateTime.now());
        health.put("message", "API funcionando corretamente");

        return ResponseEntity.ok(health);
    }
}