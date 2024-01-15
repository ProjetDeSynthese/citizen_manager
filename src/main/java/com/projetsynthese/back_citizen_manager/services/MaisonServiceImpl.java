package com.projetsynthese.back_citizen_manager.services;
import com.projetsynthese.back_citizen_manager.entity.Maison;
import com.projetsynthese.back_citizen_manager.exeption.EntityNotFoundException;
import com.projetsynthese.back_citizen_manager.repository.MaisonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaisonServiceImpl implements MaisonService {
    @Autowired
    private MaisonRepository maisonRepository;

    public void create(Maison habitat) {
        maisonRepository.save(habitat);
    }

    public List<Maison> findAll() {
        Optional<List<Maison>> optionalHabitats = Optional.of(this.maisonRepository.findAll());
        return optionalHabitats.orElseThrow(
                ()-> new EntityNotFoundException("Not Found")
        );
    }


    public Maison findById(String id) {
        Optional<Maison> optionalHabitat = maisonRepository.findById(id);
        return optionalHabitat.orElseThrow(()->new EntityNotFoundException());
    }

    public void deleteById(String id){
        maisonRepository.deleteById(id);
    }
}
