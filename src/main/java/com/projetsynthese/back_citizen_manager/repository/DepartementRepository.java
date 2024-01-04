package com.projetsynthese.back_citizen_manager.repository;

import com.projetsynthese.back_citizen_manager.entity.Departement;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface DepartementRepository extends MongoRepository<Departement, String> {
    public Optional<Departement> findByName(String name);

    Optional<Departement> findByCode(String code);

    //void delete(Optional<Departement> optionalDepartement);
}
