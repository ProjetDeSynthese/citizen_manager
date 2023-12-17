package com.projetsynthese.back_citizen_manager.controller;

import com.projetsynthese.back_citizen_manager.entity.Commune;
import com.projetsynthese.back_citizen_manager.entity.Departement;
import com.projetsynthese.back_citizen_manager.entity.Region;
import com.projetsynthese.back_citizen_manager.entity.Ville;
import com.projetsynthese.back_citizen_manager.repository.CommuneRepo;
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
public class CommuneController {

    @Autowired
    private CommuneRepo communeRepo;
    @Autowired
    private VilleRepo villeRepo;
    @Autowired
    private DepartementRepo departementRepo;
    @Autowired
    private RegionRepo regionRepo;

    @PostMapping("/addCommune")
    public ResponseEntity<Commune> saveCommune(@RequestBody Commune commune){

        if (commune == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(communeRepo.save(commune), HttpStatus.OK);
    }

//    Liste des communes d'une ville
    @GetMapping("/findAllCommuneByVille")
    public ResponseEntity<List<Commune>> getAllCommuneByVille(@RequestParam String nameVille){

        List<Commune> communes = new ArrayList<>();
        Optional<Ville> ville = villeRepo.findByName(nameVille);
        if (!ville.isPresent()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        for (Commune commune : communeRepo.findAll()){
            if (commune.getVille().getName().equals(ville.get().getName())){
                communes.add(commune);
            }
        }
        return new ResponseEntity<>(communes, HttpStatus.OK);
    }

//    Liste des communes d'un departement
    @GetMapping("/findAllCommuneByDepartement")
    public ResponseEntity<List<Commune>> getAllCommuneByDepartement(@RequestParam String nameDepart){

        List<Commune> communes = new ArrayList<>();
        Optional<Departement> departement = departementRepo.findByName(nameDepart);
        if (!departement.isPresent()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        for (Commune commune : communeRepo.findAll()){
            Ville ville = villeRepo.findByName(commune.getVille().getName()).get();
            Departement depart = departementRepo.findByName(ville.getDepartement().getName()).get();
            if (depart.getName().equals(departement.get().getName())){
                communes.add(commune);
            }
        }
        return new ResponseEntity<>(communes, HttpStatus.OK);
    }

//    Liste des communes d'une region
    @GetMapping("/findAllCommuneByRegion")
    public ResponseEntity<List<Commune>> getAllCommuneByRegion(@RequestParam String nameRegion){

        List<Commune> communes = new ArrayList<>();
        Optional<Region> region = regionRepo.findByName(nameRegion);
        if (!region.isPresent()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        for (Commune commune : communeRepo.findAll()){
            Ville ville = villeRepo.findByName(commune.getVille().getName()).get();
            Departement depart = departementRepo.findByName(ville.getDepartement().getName()).get();
            Region reg = regionRepo.findByName(depart.getRegion().getName()).get();
            if (reg.getName().equals(region.get().getName())){
                communes.add(commune);
            }
        }
        return new ResponseEntity<>(communes, HttpStatus.OK);
    }

    @GetMapping("/findCommuneById/{id}")
    public ResponseEntity<Commune> getCommuneById(@PathVariable("id") String id){

        Commune commune = communeRepo.findById(id).orElse(null);
        if (commune == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(commune, HttpStatus.OK);
    }

    @PutMapping("/updateCommune/{id}")
    public ResponseEntity<Commune> editCommune(@PathVariable("id") String id, @RequestBody Commune commune){

        Commune communeFromDB = communeRepo.findById(id).orElse(null);
        if (communeFromDB == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        communeFromDB.setName(commune.getName());
        communeFromDB.setSuperficie(commune.getSuperficie());
        communeFromDB.setCode(commune.getCode());

        Commune updateCommune = communeRepo.save(communeFromDB);
        return new ResponseEntity<>(updateCommune, HttpStatus.OK);
    }

    @DeleteMapping("/dropCommune/{id}")
    public String deleteCommune(@PathVariable("id") String id){
        communeRepo.deleteById(id);
        return "Deleted with Successfully from database";
    }
}
