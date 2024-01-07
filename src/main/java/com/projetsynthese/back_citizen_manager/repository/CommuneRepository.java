package com.projetsynthese.back_citizen_manager.repository;

import com.projetsynthese.back_citizen_manager.entity.Commune;
import com.projetsynthese.back_citizen_manager.entity.Departement;
import com.projetsynthese.back_citizen_manager.entity.Ville;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CommuneRepository extends MongoRepository<Commune, String> {
    public Optional<Commune> findByName(String name);
    Optional<Commune> findByCode(String code);
    List<Commune> findByVille(Ville ville);
}
