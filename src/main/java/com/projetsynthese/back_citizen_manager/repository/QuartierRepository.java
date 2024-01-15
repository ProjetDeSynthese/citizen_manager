package com.projetsynthese.back_citizen_manager.repository;

import com.projetsynthese.back_citizen_manager.entity.Commune;
import com.projetsynthese.back_citizen_manager.entity.Quartier;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface QuartierRepository extends MongoRepository<Quartier, String> {
    public Optional<Quartier> findByName(String name);

    Optional<Quartier> findById(String id);

    public List<Quartier> findByCommune(Commune commune);
}
