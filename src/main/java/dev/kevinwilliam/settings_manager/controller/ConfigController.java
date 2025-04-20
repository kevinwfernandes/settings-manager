package dev.kevinwilliam.settings_manager.controller;


import dev.kevinwilliam.settings_manager.model.Config;
import dev.kevinwilliam.settings_manager.service.ConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/config")
@RequiredArgsConstructor
public class ConfigController {
    private final ConfigService configService;

    // Endpoint para criar uma nova configuração
    @PostMapping
    public ResponseEntity <Config> createConfig(@RequestBody Config config){
        return ResponseEntity.ok(configService.createConfig(config));
    }
    // Endpoint para atualizar uma configuração existente
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateConfig(@PathVariable String id, @RequestBody Config newConfig) {
        configService.updateConfig(id, newConfig);
        return ResponseEntity.ok().build();
    }
    // Endpoint para ativar/desativar uma configuração
    @PutMapping("/{id}/toggle")
    public ResponseEntity<Void> toggleConfig(@PathVariable String id, @RequestParam boolean active) {
        configService.toggleConfigStatus(id, active);
        return ResponseEntity.ok().build();
    }

    // Endpoint para buscar uma configuração ativa por chave e ambiente
    @GetMapping("/{key}/{environment}/active")
    public ResponseEntity<Config> getActiveConfig(@PathVariable String key, @PathVariable String environment) {
        Config config = new Config();
        config.setKey(key);
        config.setEnvironment(environment);
        return ResponseEntity.ok(configService.getActiveConfig(config));
    }

    // Endpoint para buscar uma configuração inativa por chave e ambiente
    @GetMapping("/{key}/{environment}/inactive")
    public ResponseEntity<Config> getInactiveConfig(@PathVariable String key, @PathVariable String environment) {
        Config config = new Config();
        config.setKey(key);
        config.setEnvironment(environment);
        return ResponseEntity.ok(configService.getInactiveConfig(config));
    }

    // Endpoint para listar todas as configurações de um ambiente
    // Endpoint para listar todas as configurações de um ambiente
    @GetMapping("/{environment}")
    public ResponseEntity<List<Config>> getAllConfigs(@PathVariable String environment) {
        return ResponseEntity.ok(configService.getAllConfigs(environment));
    }

}
