package com.projetsynthese.back_citizen_manager.services;

import com.projetsynthese.back_citizen_manager.entity.Maison;
import com.projetsynthese.back_citizen_manager.entity.Proprietaire;
import com.projetsynthese.back_citizen_manager.exeption.EntityNotFoundException;
import com.projetsynthese.back_citizen_manager.repository.MaisonRepository;
import com.projetsynthese.back_citizen_manager.repository.ProprietaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProprietaireServiceImpl  implements  ProprietaireService{
    @Autowired
    private ProprietaireRepository proprietaireRepository;

    public void create(Proprietaire proprietaire) {
        proprietaireRepository.save(proprietaire);
    }

    public List<Proprietaire> findAll() {
        Optional<List<Proprietaire>> optionalProprietaires = Optional.of(this.proprietaireRepository.findAll());
        return optionalProprietaires.orElseThrow(
                ()-> new EntityNotFoundException("Not Found")
        );
    }


    public Proprietaire findById(String id) {
        Optional<Proprietaire> optionalProprietaire = proprietaireRepository.findById(id);
        return optionalProprietaire.orElseThrow(()->new EntityNotFoundException());
    }

    public void deleteById(String id){
        proprietaireRepository.deleteById(id);
    }
}
