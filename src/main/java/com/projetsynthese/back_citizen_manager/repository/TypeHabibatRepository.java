package com.projetsynthese.back_citizen_manager.repository;

import com.projetsynthese.back_citizen_manager.entity.TypeHabitat;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TypeHabibatRepository extends MongoRepository<TypeHabitat, String> {
    public Optional<TypeHabibatRepository> findByName(String name);

}
