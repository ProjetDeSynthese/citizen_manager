package com.projetsynthese.back_citizen_manager.services;

import com.projetsynthese.back_citizen_manager.entity.Commune;
import com.projetsynthese.back_citizen_manager.exeption.EntityNotFoundException;
import com.projetsynthese.back_citizen_manager.repository.CommuneRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class CommuneServiceImpl implements  CommuneService{
    private CommuneRepository communeRepository;

    public void create(Commune commune) {
        Optional<Commune> communeOptional = Optional.ofNullable(findByCode(commune.code));

        communeRepository.save(commune);
    }

    public List<Commune> findAll() {
        Optional<List<Commune>> optionalCommuneList = Optional.of(this.communeRepository.findAll());
        return optionalCommuneList.orElseThrow(
                ()-> new EntityNotFoundException("Not Found")
        );
    }

    public Commune findByCode(String code) {
        Optional<Commune> optionalCommune = communeRepository.findByCode(code);
        return optionalCommune.orElseThrow(()->new EntityNotFoundException());

    }

    public void deleteByCode(String code){
        Optional<Commune> optionalCommune = Optional.ofNullable(findByCode(code));
      //  communeRepository.delete(optionalCommune);
    }
}
