package com.projetsynthese.back_citizen_manager.repository;

import com.projetsynthese.back_citizen_manager.entity.Maison;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface MaisonRepository extends MongoRepository<Maison, String> {

    public Optional<Maison> findByProprietaire(String pro);
}
