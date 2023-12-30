package com.projetsynthese.back_citizen_manager.services;

import com.projetsynthese.back_citizen_manager.entity.Region;
import com.projetsynthese.back_citizen_manager.entity.Ville;
import com.projetsynthese.back_citizen_manager.exeption.EntityNotFoundException;
import com.projetsynthese.back_citizen_manager.repository.RegionRepository;
import com.projetsynthese.back_citizen_manager.repository.VilleRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class VilleServiceImpl implements  VilleService{

    private VilleRepository villeRepository;

    public void create(Ville ville) {
        Optional<Ville> optionalVille = Optional.ofNullable(findByCode(ville.code));

        villeRepository.save(ville);
    }

    public List<Ville> findAll() {
        Optional<List<Ville>> optionalVilles = Optional.of(this.villeRepository.findAll());
        return optionalVilles.orElseThrow(
                ()-> new EntityNotFoundException("Not Found")
        );
    }

    public Ville findByCode(String code) {
        Optional<Ville> optionalVille = villeRepository.findByCode(code);
        return optionalVille.orElseThrow(()->new EntityNotFoundException());

    }

    public void deleteByCode(String code){
        Optional<Ville> optionalVille = Optional.ofNullable(findByCode(code));
        //villeRepository.delete(optionalVille);
    }
}
