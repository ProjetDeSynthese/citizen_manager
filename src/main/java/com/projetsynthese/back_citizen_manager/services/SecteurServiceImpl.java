package com.projetsynthese.back_citizen_manager.services;

import com.projetsynthese.back_citizen_manager.entity.Citoyen;
import com.projetsynthese.back_citizen_manager.entity.Quartier;
import com.projetsynthese.back_citizen_manager.entity.Secteur;
import com.projetsynthese.back_citizen_manager.exeption.EntityNotFoundException;
import com.projetsynthese.back_citizen_manager.repository.SecteurRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SecteurServiceImpl implements  SecteurService{
    @Autowired
    private SecteurRepository secteurRepository;
    @Autowired
    private  QuartierService quartierService;

    public void create(Secteur secteur) {
       // Optional<Secteur> optionalSecteur = Optional.ofNullable(findByCode(secteur.code));

        secteurRepository.save(secteur);
    }

    public List<Secteur> findAll() {
        Optional<List<Secteur>> optionalSecteurs = Optional.of(this.secteurRepository.findAll());
        return optionalSecteurs.orElseThrow(
                ()-> new EntityNotFoundException("Not Found")
        );
    }

    public Secteur findByCode(String code) {
        Optional<Secteur> optionalDepartement = secteurRepository.findByCode(code);
        return optionalDepartement.orElseThrow(()->new EntityNotFoundException());

    }
    public Secteur findById(String id) {
        Optional<Secteur> optionalSecteur = secteurRepository.findById(id);
        return optionalSecteur.orElseThrow(()->new EntityNotFoundException());
    }

    public void deleteById(String id){
        secteurRepository.deleteById(id);
    }


    public List<Secteur> findByQuartier(String quartier_id) {
        Quartier quartier = this.quartierService.findById(quartier_id);
        Optional<List<Secteur>> optionalSecteurs = Optional.of(this.secteurRepository.findByQuartier(quartier));
        return optionalSecteurs.orElseThrow(
                ()-> new EntityNotFoundException("Not Found")
        );
    }
}
