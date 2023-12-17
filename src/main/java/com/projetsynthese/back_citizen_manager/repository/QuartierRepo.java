package com.projetsynthese.back_citizen_manager.repository;

import com.projetsynthese.back_citizen_manager.entity.Quartier;
import com.projetsynthese.back_citizen_manager.entity.Region;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface QuartierRepo extends MongoRepository<Quartier, String> {
    public Optional<Quartier> findByName(String name);
}
