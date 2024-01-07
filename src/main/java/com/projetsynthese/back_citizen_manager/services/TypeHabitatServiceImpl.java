package com.projetsynthese.back_citizen_manager.services;

import com.projetsynthese.back_citizen_manager.entity.Secteur;
import com.projetsynthese.back_citizen_manager.entity.TypeHabitat;
import com.projetsynthese.back_citizen_manager.exeption.EntityNotFoundException;
import com.projetsynthese.back_citizen_manager.repository.SecteurRepository;
import com.projetsynthese.back_citizen_manager.repository.TypeHabibatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
 @Service
public class TypeHabitatServiceImpl implements TypeHabitatService {
    @Autowired
    private TypeHabibatRepository typeHabibatRepository;

    public void create(TypeHabitat typeHabitat) {

        typeHabibatRepository.save(typeHabitat);
    }

    public List<TypeHabitat> findAll() {
        Optional<List<TypeHabitat>> optionalTypeHabitats = Optional.of(this.typeHabibatRepository.findAll());
        return optionalTypeHabitats.orElseThrow(
                ()-> new EntityNotFoundException("Not Found")
        );
    }


    public TypeHabitat findById(String id) {
        Optional<TypeHabitat> optionalTypeHabitat = typeHabibatRepository.findById(id);
        return optionalTypeHabitat.orElseThrow(()->new EntityNotFoundException());
    }

    public void deleteById(String id){
        typeHabibatRepository.deleteById(id);
    }
}
