package dev.kevinwilliam.settings_manager.repository;


import dev.kevinwilliam.settings_manager.model.Config;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ConfigRepository extends MongoRepository<Config, String> {
    // Consultas básicas

    Optional<Config> findByKeyAndEnvironment(String key, String environment);
    List<Config> findAllByEnvironment(String environment);
    // Consultas com status "active" ou "inactive"
    Optional<Config> findByKeyAndEnvironmentAndActiveTrue(String key, String environment);
    Optional<Config> findByKeyAndEnvironmentAndActiveFalse(String key, String environment);


    List<Config> findAllByEnvironmentAndActiveTrue(String environment);
    List<Config> findAllByEnvironmentAndActiveFalse(String environment);

    // Validação de existência
    boolean existsByKeyAndEnvironment(String key, String environment);
}
