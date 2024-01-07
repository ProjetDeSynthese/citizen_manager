package com.projetsynthese.back_citizen_manager.services;

import com.projetsynthese.back_citizen_manager.entity.Citoyen;
import com.projetsynthese.back_citizen_manager.entity.Departement;
import com.projetsynthese.back_citizen_manager.entity.Region;
import com.projetsynthese.back_citizen_manager.entity.Ville;
import com.projetsynthese.back_citizen_manager.exeption.EntityNotFoundException;
import com.projetsynthese.back_citizen_manager.repository.RegionRepository;
import com.projetsynthese.back_citizen_manager.repository.VilleRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class VilleServiceImpl implements  VilleService{
    @Autowired
    private VilleRepository villeRepository;
    @Autowired
    private  DepartementService departementService;

    public void create(Ville ville) {
       // Optional<Ville> optionalVille = Optional.ofNullable(findByCode(ville.code));

        villeRepository.save(ville);
    }

    public List<Ville> findAll() {
        Optional<List<Ville>> optionalVilles = Optional.of(this.villeRepository.findAll());
        return optionalVilles.orElseThrow(
                ()-> new EntityNotFoundException("Not Found")
        );
    }

    public List<Ville> findByDepartement(String departement_id) {
        Departement departement = this.departementService.findById(departement_id);
        Optional<List<Ville>> optionalVilles = Optional.of(this.villeRepository.findByDepartement(departement));
        return optionalVilles.orElseThrow(
                ()-> new EntityNotFoundException("Not Found")
        );
    }

    public Ville findByCode(String code) {
        Optional<Ville> optionalVille = villeRepository.findByCode(code);
        return optionalVille.orElseThrow(()->new EntityNotFoundException());

    }
    public Ville findById(String id) {
        Optional<Ville> optionalVille = villeRepository.findById(id);
        return optionalVille.orElseThrow(()->new EntityNotFoundException());
    }

    public void deleteById(String id){
        villeRepository.deleteById(id);
    }
}
