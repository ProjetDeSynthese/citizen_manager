package com.projetsynthese.back_citizen_manager.services;

import com.projetsynthese.back_citizen_manager.entity.Citoyen;
import com.projetsynthese.back_citizen_manager.exeption.EntityNotFoundException;
import com.projetsynthese.back_citizen_manager.repository.CitoyenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CitoyenServiceImpl implements CitoyenService {
    @Autowired
    private CitoyenRepository citoyenRepository;

    public void create(Citoyen citoyen) {
        //Optional<Citoyen> optionalCitoyen = Optional.ofNullable(findByCni(citoyen.numCni));

        citoyenRepository.save(citoyen);
    }

    public List<Citoyen> findAll() {
        Optional<List<Citoyen>> optionalCitoyens = Optional.of(this.citoyenRepository.findAll());
        return optionalCitoyens.orElseThrow(
                ()-> new EntityNotFoundException("Not Found")
        );
    }

    public Citoyen findByCni(String cni) {
        Optional<Citoyen> optionalCitoyen = Optional.ofNullable(citoyenRepository.findByNumCni(cni));
        return optionalCitoyen.orElseThrow(()->new EntityNotFoundException());

    }
    public Citoyen findById(String id) {
        Optional<Citoyen> optionalCitoyen = citoyenRepository.findById(id);
        return optionalCitoyen.orElseThrow(()->new EntityNotFoundException());
    }
    public void deleteById(String id){
        citoyenRepository.deleteById(id);
    }

}
