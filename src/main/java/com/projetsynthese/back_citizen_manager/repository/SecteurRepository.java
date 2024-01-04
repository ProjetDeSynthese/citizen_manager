package com.projetsynthese.back_citizen_manager.repository;

import com.projetsynthese.back_citizen_manager.entity.Secteur;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SecteurRepository extends MongoRepository<Secteur, String> {
    public Optional<Secteur> findByName(String name);

    Optional<Secteur> findByCode(String code);
}
