package com.projetsynthese.back_citizen_manager.repository;

import com.projetsynthese.back_citizen_manager.entity.Ville;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface VilleRepository extends MongoRepository<Ville, String> {
    public Optional<Ville> findByName(String name);

    Optional<Ville> findByCode(String code);

    //void delete(Optional<Ville> optionalVille);
}
