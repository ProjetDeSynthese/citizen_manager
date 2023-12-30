package com.projetsynthese.back_citizen_manager.services;

import com.projetsynthese.back_citizen_manager.entity.Departement;
import com.projetsynthese.back_citizen_manager.exeption.EntityNotFoundException;
import com.projetsynthese.back_citizen_manager.repository.DepartementRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class DepartementServiceImpl implements DepartementService{
    private DepartementRepository departementRepository;

    public void create(Departement departement) {
        Optional<Departement> optionalDepartement = Optional.ofNullable(findByCode(departement.code));

        departementRepository.save(departement);
    }

    public List<Departement> findAll() {
        Optional<List<Departement>> optionalDepartements = Optional.of(this.departementRepository.findAll());
        return optionalDepartements.orElseThrow(
                ()-> new EntityNotFoundException("Not Found")
        );
    }

    public Departement findByCode(String code) {
        Optional<Departement> optionalDepartement = departementRepository.findByCode(code);
        return optionalDepartement.orElseThrow(()->new EntityNotFoundException());

    }

    public void deleteByCode(String code){
        Optional<Departement> optionalDepartement = Optional.ofNullable(findByCode(code));
        //departementRepository.delete(optionalDepartement);
    }
}
