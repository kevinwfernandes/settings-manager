package dev.kevinwilliam.settings_manager.controller;


import dev.kevinwilliam.settings_manager.model.Config;
import dev.kevinwilliam.settings_manager.service.ConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
