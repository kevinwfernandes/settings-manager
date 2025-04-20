package dev.kevinwilliam.settings_manager.service;

import com.mongodb.DuplicateKeyException;

import dev.kevinwilliam.settings_manager.model.Config;
import dev.kevinwilliam.settings_manager.repository.ConfigRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class ConfigService {

    @NonNull private final ConfigRepository configRepository;

    // Cria uma nova configuração
    public Config createConfig(Config config) {
        if (config.getKey() == null || config.getKey().isBlank() || config.getEnvironment() == null || config.getEnvironment().isBlank()) {
            throw new IllegalArgumentException("Chave ou ambiente inválidos!");
        }

        if (configRepository.existsByKeyAndEnvironment(config.getKey(), config.getEnvironment())) {
            throw new IllegalArgumentException("Configuração já existe para chave: " + config.getKey() + " e ambiente: " + config.getEnvironment());
        }


        config.setActive(true);

        try {
         return   configRepository.save(config);
        } catch (DuplicateKeyException e) {
            throw new IllegalArgumentException("Conflito ao salvar configuração: " + e.getMessage());
        }
    }

    // Busca configuração ativa
    public Config getActiveConfig(Config config) {
        return configRepository.findByKeyAndEnvironmentAndActiveTrue(config.getKey(), config.getEnvironment())
                .orElseThrow(() -> new IllegalArgumentException("Configuração ativa não encontrada!"));
    }

    // Busca configuração inativa
    public Config getInactiveConfig(Config config) {
        return configRepository.findByKeyAndEnvironmentAndActiveFalse(config.getKey(), config.getEnvironment())
                .orElseThrow(() -> new IllegalArgumentException("Configuração inativa não encontrada!"));
    }

    // Lista todas as configurações ativas de um ambiente
    public List<Config> getAllActiveConfigs(String environment) {
        return configRepository.findAllByEnvironmentAndActiveTrue(environment);
    }

    // Lista todas as configurações inativas de um ambiente
    public List<Config> getAllInactiveConfigs(String environment) {
        return configRepository.findAllByEnvironmentAndActiveFalse(environment);
    }

    // Lista todas as configurações (ativas e inativas) de um ambiente
    public List<Config> getAllConfigs(String environment) {
        return configRepository.findAllByEnvironment(environment);
    }

    // Atualiza o valor de uma configuração ativa
    public void updateConfig(String key, String value, String environment) {
        Config config = configRepository.findByKeyAndEnvironmentAndActiveTrue(key, environment)
                .orElseThrow(() -> new IllegalArgumentException("Configuração ativa não encontrada para atualização!"));
        config.setValue(value);
        configRepository.save(config);
    }

    // Desativa uma configuração (soft delete)
    public void deactivateConfig(String key, String environment) {
        Config config = configRepository.findByKeyAndEnvironmentAndActiveTrue(key, environment)
                .orElseThrow(() -> new IllegalArgumentException("Configuração ativa não encontrada para desativação!"));
        config.setActive(false);
        configRepository.save(config);
    }

    // Alterna o status de uma configuração (ativa/inativa)
    public void toggleConfigStatus(String key, String environment) {
        Config config = configRepository.findByKeyAndEnvironment(key, environment)
                .orElseThrow(() -> new IllegalArgumentException("Configuração não encontrada para alteração de status!"));
        config.setActive(!config.isActive());
        configRepository.save(config);
    }
}