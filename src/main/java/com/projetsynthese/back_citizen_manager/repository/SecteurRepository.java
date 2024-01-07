package com.projetsynthese.back_citizen_manager.repository;

import com.projetsynthese.back_citizen_manager.entity.Quartier;
import com.projetsynthese.back_citizen_manager.entity.Secteur;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface SecteurRepository extends MongoRepository<Secteur, String> {
    public Optional<Secteur> findByName(String name);

    Optional<Secteur> findByCode(String code);
    Optional<Secteur> findById(String id);
    List<Secteur> findByQuartier(Quartier quartier);
}
