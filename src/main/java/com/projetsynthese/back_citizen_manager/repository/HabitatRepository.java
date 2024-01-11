package com.projetsynthese.back_citizen_manager.repository;

import com.projetsynthese.back_citizen_manager.entity.Habitat;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface HabitatRepository extends MongoRepository<Habitat, String> {

    //public List<Habitat> findByAdresse(String adresse);
    public Optional<Habitat> findByProprietaire(String pro);
}
