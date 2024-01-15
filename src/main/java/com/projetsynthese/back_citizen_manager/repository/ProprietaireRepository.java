package com.projetsynthese.back_citizen_manager.repository;

import com.projetsynthese.back_citizen_manager.entity.Maison;
import com.projetsynthese.back_citizen_manager.entity.Proprietaire;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface ProprietaireRepository extends MongoRepository<Proprietaire, String> {

    public Optional<Proprietaire> findById(String id);
}

