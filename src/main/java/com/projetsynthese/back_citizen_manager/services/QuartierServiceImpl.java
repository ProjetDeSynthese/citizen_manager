package com.projetsynthese.back_citizen_manager.services;

import com.projetsynthese.back_citizen_manager.entity.Citoyen;
import com.projetsynthese.back_citizen_manager.entity.Commune;
import com.projetsynthese.back_citizen_manager.entity.Quartier;
import com.projetsynthese.back_citizen_manager.exeption.EntityNotFoundException;
import com.projetsynthese.back_citizen_manager.repository.QuartierRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuartierServiceImpl implements QuartierService {
    @Autowired
    private QuartierRepository quartierRepository;
    @Autowired
    private  CommuneService communeService;
    public void create(Quartier quartier) {
       // Optional<Quartier> optionalQuartier = Optional.ofNullable(findByCode(quartier.code));

        quartierRepository.save(quartier);
    }

    public List<Quartier> findAll() {
        Optional<List<Quartier>> optionalQuartiers = Optional.of(this.quartierRepository.findAll());
        return optionalQuartiers.orElseThrow(
                ()-> new EntityNotFoundException("Not Found")
        );
    }

    public Quartier findByCode(String code) {
        Optional<Quartier> optionalQuartier = quartierRepository.findByCode(code);
        return optionalQuartier.orElseThrow(()->new EntityNotFoundException());

    }
    public Quartier findById(String id) {
        Optional<Quartier> optionalQuartier = quartierRepository.findById(id);
        return optionalQuartier.orElseThrow(()->new EntityNotFoundException());
    }
    public void deleteById(String id){
        quartierRepository.deleteById(id);
    }

    public List<Quartier> findByCommune(String commune_id) {
        Commune commune = this.communeService.findById(commune_id);
        Optional<List<Quartier>> optionalQuartiers = Optional.of(this.quartierRepository.findByCommune(commune));
        return optionalQuartiers.orElseThrow(
                ()-> new EntityNotFoundException("Not Found")
        );
    }
}
