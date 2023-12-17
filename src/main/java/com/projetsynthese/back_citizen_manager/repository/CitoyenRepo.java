package com.projetsynthese.back_citizen_manager.repository;

import com.projetsynthese.back_citizen_manager.entity.Citoyen;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

//@RepositoryRestResource
public interface CitoyenRepo extends MongoRepository<Citoyen, String> {

    public Citoyen findByNumCni(String numCni);
//    public Citoyen findByNumCni(String numCni);
}
