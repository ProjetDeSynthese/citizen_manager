package com.projetsynthese.back_citizen_manager.repository;

import com.projetsynthese.back_citizen_manager.entity.Departement;
import com.projetsynthese.back_citizen_manager.entity.Ville;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface VilleRepository extends MongoRepository<Ville, String> {
    public Optional<Ville> findByName(String name);
    Optional<Ville> findByCode(String code);
    List<Ville> findByDepartement(Departement departement);

    //void delete(Optional<Ville> optionalVille);
}
