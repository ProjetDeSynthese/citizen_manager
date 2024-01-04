package com.projetsynthese.back_citizen_manager.repository;

import com.projetsynthese.back_citizen_manager.entity.Citoyen;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface CitoyenRepository extends MongoRepository<Citoyen, String> {

    public Citoyen findByNumCni(String numCni);
//    public Citoyen findByNumCni(String numCni);
}
