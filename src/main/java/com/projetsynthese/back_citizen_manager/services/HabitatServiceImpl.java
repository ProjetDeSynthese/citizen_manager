package com.projetsynthese.back_citizen_manager.services;
import com.projetsynthese.back_citizen_manager.entity.Citoyen;
import com.projetsynthese.back_citizen_manager.entity.Habitat;
import com.projetsynthese.back_citizen_manager.exeption.EntityNotFoundException;
import com.projetsynthese.back_citizen_manager.repository.HabitatRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HabitatServiceImpl implements HabitatService {
    @Autowired
    private HabitatRepository habitatRepository;

    public void create(Habitat habitat) {
       // Optional<Habitat> ha = Optional.ofNullable(findByCode(habitat.code));

        habitatRepository.save(habitat);
    }

    public List<Habitat> findAll() {
        Optional<List<Habitat>> optionalHabitats = Optional.of(this.habitatRepository.findAll());
        return optionalHabitats.orElseThrow(
                ()-> new EntityNotFoundException("Not Found")
        );
    }

   /* public Habitat findByAddress(String address) {
        List<Habitat> optionalHabitat = habitatRepository.findByAdresse(address);
        return (Habitat) optionalHabitat;

    }*/
    public Habitat findById(String id) {
        Optional<Habitat> optionalHabitat = habitatRepository.findById(id);
        return optionalHabitat.orElseThrow(()->new EntityNotFoundException());
    }

    public void deleteById(String id){
        habitatRepository.deleteById(id);
    }
}
