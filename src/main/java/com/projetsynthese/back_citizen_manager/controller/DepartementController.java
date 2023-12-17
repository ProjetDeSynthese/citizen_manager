package com.projetsynthese.back_citizen_manager.controller;

import com.projetsynthese.back_citizen_manager.entity.Departement;
import com.projetsynthese.back_citizen_manager.entity.Region;
import com.projetsynthese.back_citizen_manager.repository.DepartementRepo;
import com.projetsynthese.back_citizen_manager.repository.RegionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class DepartementController {

    @Autowired
    private DepartementRepo departementRepo;
    @Autowired
    private RegionRepo regionRepo;

    @PostMapping("/addDepartement")
    public ResponseEntity<Departement> saveDepartement(@RequestBody Departement departement){

        if(departement == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(departementRepo.save(departement), HttpStatus.OK);
    }

//    Liste des departements par region
    @GetMapping("/findAllDepartementByRegion")
    public ResponseEntity<List<Departement>> getAllDepartByRegion(@RequestParam String nameRegion){

        List<Departement> departements = new ArrayList<>();
        Optional<Region> region = regionRepo.findByName(nameRegion);
        if (!region.isPresent()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        for (Departement departement : departementRepo.findAll()){
            if (departement.getRegion().getName().equals(region.get().getName())){
                departements.add(departement);
            }
        }
        return new ResponseEntity<>(departements, HttpStatus.OK);
    }

    @GetMapping("/findDepartementById/{id}")
    public ResponseEntity<Departement> getDepartById(@PathVariable("id") String id){

        Departement departement = departementRepo.findById(id).orElse(null);
        if (departement == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(departement, HttpStatus.OK);
    }

    @PutMapping("/updateDepartement/{id}")
    public ResponseEntity<Departement> editDepart(@PathVariable("id") String id, @RequestBody Departement departement){

        Departement departDB = departementRepo.findById(id).orElse(null);
        if (departDB == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        departDB.setName(departement.getName());
        departDB.setSuperficie(departement.getSuperficie());
        departDB.setCode(departement.getCode());

        Departement updateDepart = departementRepo.save(departDB);
        return new ResponseEntity<>(updateDepart, HttpStatus.OK);
    }

    @DeleteMapping("/dropDepartement/{id}")
    public String deleteDepart(@PathVariable("id") String id){
        departementRepo.deleteById(id);
        return "Deleted with Successfully from database";
    }
}
