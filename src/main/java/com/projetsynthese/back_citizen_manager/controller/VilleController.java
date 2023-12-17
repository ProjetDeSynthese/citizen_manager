package com.projetsynthese.back_citizen_manager.controller;

import com.projetsynthese.back_citizen_manager.entity.*;
import com.projetsynthese.back_citizen_manager.repository.DepartementRepo;
import com.projetsynthese.back_citizen_manager.repository.RegionRepo;
import com.projetsynthese.back_citizen_manager.repository.VilleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class VilleController {

    @Autowired
    private DepartementRepo departementRepo;
    @Autowired
    private VilleRepo villeRepo;
    @Autowired
    private RegionRepo regionRepo;

    @PostMapping("/addVille")
    public ResponseEntity<Ville> saveVille(@RequestBody Ville ville){

        if (ville == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(villeRepo.save(ville), HttpStatus.OK);
    }

    //    Liste des villes par departement
    @GetMapping("/findVilleByDepart")
    public ResponseEntity<List<Ville>> getVilleByDepart(@RequestParam String nameDepart){

        List<Ville> villes = new ArrayList<>();
        Optional<Departement> departement = departementRepo.findByName(nameDepart);
        if (!departement.isPresent()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        for (Ville ville : villeRepo.findAll()){
            if (ville.getDepartement().getName().equals(departement.get().getName())){
                villes.add(ville);
            }
        }
        return new ResponseEntity<>(villes, HttpStatus.OK);
    }

    //    Liste des villes par region
    @GetMapping("/findVilleByRegion")
    public ResponseEntity<List<Ville>> getVilleByRegion(@RequestParam String nameRegion){

        List<Ville> villes = new ArrayList<>();
        Optional<Region> region = regionRepo.findByName(nameRegion);
        if (!region.isPresent()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        for (Ville ville : villeRepo.findAll()){
            Departement depart = departementRepo.findByName(ville.getDepartement().getName()).get();
            Region reg = regionRepo.findByName(depart.getRegion().getName()).get();
            if (reg.getName().equals(region.get().getName())){
                villes.add(ville);
            }
        }
        return new ResponseEntity<>(villes, HttpStatus.OK);
    }

    @GetMapping("/findVilleById/{id}")
    public ResponseEntity<Ville> getVilleById(@PathVariable("id") String id){

        Ville ville = villeRepo.findById(id).orElse(null);
        if (ville == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ville, HttpStatus.OK);
    }

    @PutMapping("/updateVille/{id}")
    public ResponseEntity<Ville> editVille(@PathVariable("id") String id, @RequestBody Ville ville){

        Ville vil = villeRepo.findById(id).orElse(null);
        if (vil == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        vil.setCode(ville.getCode());
        vil.setName(ville.getName());
        vil.setSuperficie(ville.getSuperficie());

        return new ResponseEntity<>(villeRepo.save(vil), HttpStatus.OK);
    }

    @DeleteMapping("/dropVille/{id}")
    public String deleteVille(@PathVariable("id") String id){

        villeRepo.deleteById(id);
        return "Deleted with Successfully from database";
    }
}
