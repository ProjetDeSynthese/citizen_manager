package com.projetsynthese.back_citizen_manager.controller;

import com.projetsynthese.back_citizen_manager.entity.*;
import com.projetsynthese.back_citizen_manager.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class SecteurController {

    @Autowired
    private SecteurRepo secteurRepo;
    @Autowired
    private QuartierRepo quartierRepo;
    @Autowired
    private CommuneRepository communeRepo;
    @Autowired
    private VilleRepository villeRepo;
    @Autowired
    private DepartementRepository departementRepo;
    @Autowired
    private RegionRepository regionRepo;

    @PostMapping("/addSecteur")
    public ResponseEntity<Secteur> saveSecteur(@RequestBody Secteur secteur){
        if (secteur == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(secteurRepo.save(secteur), HttpStatus.OK);
    }

//    Liste des secteurs dans un quartier
    @GetMapping("/findSecteurByQuartier")
    public ResponseEntity<List<Secteur>> getSecteurByQuartier(@RequestParam String nameQuart){
        List<Secteur> secteurs = new ArrayList<>();
        Optional<Quartier> quartier = quartierRepo.findByName(nameQuart);
        if (!quartier.isPresent()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        for (Secteur secteur : secteurRepo.findAll()){
            Quartier quart = quartierRepo.findByName(secteur.getQuartier().getName()).get();
            if (quart.getName().equals(quartier.get().getName())){
                secteurs.add(secteur);
            }
        }
        return new ResponseEntity<>(secteurs, HttpStatus.OK);
    }

    //    Liste des secteurs dans une commune
    @GetMapping("/findSecteurByCommune")
    public ResponseEntity<List<Secteur>> getSecteurByCommune(@RequestParam String nameCom){
        List<Secteur> secteurs = new ArrayList<>();
        Optional<Commune> commune = communeRepo.findByName(nameCom);
        if (!commune.isPresent()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        for (Secteur secteur : secteurRepo.findAll()){
            Quartier quart = quartierRepo.findByName(secteur.getQuartier().getName()).get();
            Commune com = communeRepo.findByName(quart.getCommune().getName()).get();
            if (com.getName().equals(commune.get().getName())){
                secteurs.add(secteur);
            }
        }
        return new ResponseEntity<>(secteurs, HttpStatus.OK);
    }

    //    Liste des secteurs dans une ville
    @GetMapping("/findSecteurByVille")
    public ResponseEntity<List<Secteur>> getSecteurByVille(@RequestParam String nameVille){
        List<Secteur> secteurs = new ArrayList<>();
        Optional<Ville> ville = villeRepo.findByName(nameVille);
        if (!ville.isPresent()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        for (Secteur secteur : secteurRepo.findAll()){
            Quartier quart = quartierRepo.findByName(secteur.getQuartier().getName()).get();
            Commune com = communeRepo.findByName(quart.getCommune().getName()).get();
            Ville vil = villeRepo.findByName(com.getVille().getName()).get();
            if (vil.getName().equals(ville.get().getName())){
                secteurs.add(secteur);
            }
        }
        return new ResponseEntity<>(secteurs, HttpStatus.OK);
    }

    //    Liste des secteurs dans un departement
    @GetMapping("/findSecteurByDepartement")
    public ResponseEntity<List<Secteur>> getSecteurByDepart(@RequestParam String nameDepart){
        List<Secteur> secteurs = new ArrayList<>();
        Optional<Departement> departement = departementRepo.findByName(nameDepart);
        if (!departement.isPresent()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        for (Secteur secteur : secteurRepo.findAll()){
            Quartier quart = quartierRepo.findByName(secteur.getQuartier().getName()).get();
            Commune com = communeRepo.findByName(quart.getCommune().getName()).get();
            Ville vil = villeRepo.findByName(com.getVille().getName()).get();
            Departement depart = departementRepo.findByName(vil.getDepartement().getName()).get();
            if (depart.getName().equals(departement.get().getName())){
                secteurs.add(secteur);
            }
        }
        return new ResponseEntity<>(secteurs, HttpStatus.OK);
    }

    //    Liste des secteurs dans une region
    @GetMapping("/findSecteurByRegion")
    public ResponseEntity<List<Secteur>> getSecteurByRegion(@RequestParam String nameRegion){
        List<Secteur> secteurs = new ArrayList<>();
        Optional<Region> region = regionRepo.findByName(nameRegion);
        if (!region.isPresent()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        for (Secteur secteur : secteurRepo.findAll()){
            Quartier quart = quartierRepo.findByName(secteur.getQuartier().getName()).get();
            Commune com = communeRepo.findByName(quart.getCommune().getName()).get();
            Ville vil = villeRepo.findByName(com.getVille().getName()).get();
            Departement depart = departementRepo.findByName(vil.getDepartement().getName()).get();
            Region reg = regionRepo.findByName(depart.getRegion().getName()).get();
            if (reg.getName().equals(region.get().getName())){
                secteurs.add(secteur);
            }
        }
        return new ResponseEntity<>(secteurs, HttpStatus.OK);
    }

    @GetMapping("/findSecteurById/{id}")
    public ResponseEntity<Secteur> getSecteurById(@PathVariable("id") String id){
        Secteur secteur = secteurRepo.findById(id).orElse(null);
        if (secteur == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(secteur, HttpStatus.OK);
    }

    @PutMapping("/updateSecteur/{id}")
    public ResponseEntity<Secteur> editSecteur(@PathVariable("id") String id, @RequestBody Secteur secteur){
        Secteur sec = secteurRepo.findById(id).orElse(null);
        if (sec == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        sec.setName(secteur.getName());
        sec.setSuperficie(secteur.getSuperficie());
        sec.setCode(secteur.getCode());

        return new ResponseEntity<>(secteurRepo.save(sec), HttpStatus.OK);
    }

    @DeleteMapping("/dropSecteur")
    public String deleteSecteur(@PathVariable("id") String id){
        secteurRepo.deleteById(id);
        return "Deleted with Successfully from database";
    }
}
