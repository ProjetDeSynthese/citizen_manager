package com.projetsynthese.back_citizen_manager.services;

import com.projetsynthese.back_citizen_manager.entity.Citoyen;
import com.projetsynthese.back_citizen_manager.entity.Departement;
import com.projetsynthese.back_citizen_manager.entity.Region;
import com.projetsynthese.back_citizen_manager.exeption.EntityNotFoundException;
import com.projetsynthese.back_citizen_manager.repository.DepartementRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartementServiceImpl implements DepartementService{
    @Autowired
    private DepartementRepository departementRepository;
    @Autowired
    private RegionService regionService;

    public void create(Departement departement) {
        //Optional<Departement> optionalDepartement = Optional.ofNullable(findByCode(departement.code));

        departementRepository.save(departement);
    }

    public List<Departement> findAll() {
        Optional<List<Departement>> optionalDepartements = Optional.of(this.departementRepository.findAll());
        return optionalDepartements.orElseThrow(
                ()-> new EntityNotFoundException("Not Found")
        );
    }

    public List<Departement> findByRegion(String region_id){
        Region region = this.regionService.findById(region_id);
        Optional<List<Departement>> optionalDepartements = Optional.of(this.departementRepository.findByRegion(region));
        return optionalDepartements.orElseThrow(
                ()-> new EntityNotFoundException("Not Found")
        );
    }

    public Departement findByCode(String code) {
        Optional<Departement> optionalDepartement = departementRepository.findByCode(code);
        return optionalDepartement.orElseThrow(()->new EntityNotFoundException());

    }
    public Departement findById(String id) {
        Optional<Departement> optionalDepartement = departementRepository.findById(id);
        return optionalDepartement.orElseThrow(()->new EntityNotFoundException());
    }

    public void deleteById(String id){
        departementRepository.deleteById(id);
    }
}
