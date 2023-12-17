package com.projetsynthese.back_citizen_manager.repository;

import com.projetsynthese.back_citizen_manager.entity.Departement;
import com.projetsynthese.back_citizen_manager.entity.Region;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface DepartementRepo extends MongoRepository<Departement, String> {
    public Optional<Departement> findByName(String name);
}
