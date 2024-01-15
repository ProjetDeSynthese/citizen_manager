package com.projetsynthese.back_citizen_manager.repository;

import com.projetsynthese.back_citizen_manager.entity.TypeMaison;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TypeMaisonRepository extends MongoRepository<TypeMaison, String> {
    public Optional<TypeMaisonRepository> findByName(String name);

}
