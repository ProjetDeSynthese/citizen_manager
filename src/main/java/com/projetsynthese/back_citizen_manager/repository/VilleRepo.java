package com.projetsynthese.back_citizen_manager.repository;

import com.projetsynthese.back_citizen_manager.entity.Region;
import com.projetsynthese.back_citizen_manager.entity.Ville;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface VilleRepo extends MongoRepository<Ville, String> {
    public Optional<Ville> findByName(String name);
}
