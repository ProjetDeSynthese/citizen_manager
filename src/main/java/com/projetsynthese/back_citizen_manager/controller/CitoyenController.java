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
public class CitoyenController {

    @Autowired
    private CitoyenRepo citoyenRepo;
    @Autowired
    private RegionRepo regionRepo;
    @Autowired
    private SecteurRepo secteurRepo;
    @Autowired
    private DepartementRepo departementRepo;
    @Autowired
    private VilleRepo villeRepo;
    @Autowired
    private QuartierRepo quartierRepo;
    @Autowired
    private CommuneRepo communeRepo;
    @Autowired
    private HabitatRepo habitatRepo;

    //Ajouter un citoyen
    @PostMapping("/addCitoyen")
    public ResponseEntity<Citoyen> saveCitoyen(@RequestBody Citoyen citoyen){

        if (citoyen == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        Citoyen citoyenSave = citoyenRepo.save(citoyen);
        return new ResponseEntity<>(citoyenSave, HttpStatus.OK);
    }

    //Liste des citoyens
    @GetMapping("/findAllCitoyen")
    public List<Citoyen> getAllCitoyen(){
        return citoyenRepo.findAll();
    }

    @GetMapping("/findCitoyenById/{id}")
    public ResponseEntity<Citoyen> getCitoyenById(@PathVariable String id){

        Citoyen citoyen = citoyenRepo.findById(id).orElse(null);
        if (citoyen == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(citoyen, HttpStatus.OK);
    }

    //Rechercher un citoyen par numero cni
    @GetMapping("/findCitoyenByNumCni")
    public ResponseEntity<Citoyen> getCitoyenByNumCni(@RequestParam String numCni){
        Citoyen citoyen = citoyenRepo.findByNumCni(numCni);
        if (citoyen == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(citoyen, HttpStatus.OK);
    }

    //Statistique: nombre d'habitant dans un secteur
    @GetMapping("/findCitizenNumber/secteur")
    public ResponseEntity<Integer> getCitizenNumberOfSecteur(@RequestParam String name){

        List<Citoyen> citoyens = new ArrayList<>();
        Optional<Secteur> secteur = secteurRepo.findByName(name);
        if (!secteur.isPresent()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        for (Citoyen citoyen : citoyenRepo.findAll()){
            for (Habitat habitat : citoyen.getHabitats()){
                if (habitat.getSecteur().getName().equals(secteur.get().getName())){
                    citoyens.add(citoyen);
                }
            }
        }
        return new ResponseEntity<>(citoyens.size(), HttpStatus.OK);
    }

    //Statistique: nombre d'habitant dans un quartier
    @GetMapping("/findCitizenNumber/quartier")
    public ResponseEntity<Integer> getCitizenNumberOfQuartier(@RequestParam String name){

        List<Citoyen> citoyens = new ArrayList<>();
        Optional<Quartier> quartier = quartierRepo.findByName(name);
        if (!quartier.isPresent()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        for (Citoyen citoyen : citoyenRepo.findAll()){
            for (Habitat habitat : citoyen.getHabitats()){
                Secteur secteur = secteurRepo.findByName(habitat.getSecteur().getName()).get();
                Quartier quart = quartierRepo.findByName(secteur.getQuartier().getName()).get();
                if (quart.getName().equals(quartier.get().getName())){
                    citoyens.add(citoyen);
                }
            }
        }
        return new ResponseEntity<>(citoyens.size(), HttpStatus.OK);
    }

    //Statistique: nombre d'habitant dans une commune
    @GetMapping("/findCitizenNumber/commune")
    public ResponseEntity<Integer> getCitizenNumberOfCommune(@RequestParam String name){

        List<Citoyen> citoyens = new ArrayList<>();
        Optional<Commune> commune = communeRepo.findByName(name);
        if (!commune.isPresent()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        for (Citoyen citoyen : citoyenRepo.findAll()){
            for (Habitat habitat : citoyen.getHabitats()){
                Secteur secteur = secteurRepo.findByName(habitat.getSecteur().getName()).get();
                Quartier quart = quartierRepo.findByName(secteur.getQuartier().getName()).get();
                Commune com = communeRepo.findByName(quart.getCommune().getName()).get();
                if (com.getName().equals(commune.get().getName())){
                    citoyens.add(citoyen);
                }
            }
        }
        return new ResponseEntity<>(citoyens.size(), HttpStatus.OK);
    }

    //Statistique: nombre d'habitant dans une ville
    @GetMapping("/findCitizenNumber/ville")
    public ResponseEntity<Integer> getCitizenNumberOfVille(@RequestParam String name){

        List<Citoyen> citoyens = new ArrayList<>();
        Optional<Ville> ville = villeRepo.findByName(name);
        if (!ville.isPresent()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        for (Citoyen citoyen : citoyenRepo.findAll()){
            for (Habitat habitat : citoyen.getHabitats()){
                Secteur secteur = secteurRepo.findByName(habitat.getSecteur().getName()).get();
                Quartier quart = quartierRepo.findByName(secteur.getQuartier().getName()).get();
                Commune com = communeRepo.findByName(quart.getCommune().getName()).get();
                Ville vil = villeRepo.findByName(com.getVille().getName()).get();
                if (vil.getName().equals(ville.get().getName())){
                    citoyens.add(citoyen);
                }
            }
        }
        return new ResponseEntity<>(citoyens.size(), HttpStatus.OK);
    }

    //Statistique: nombre d'habitant dans une departement
    @GetMapping("/findCitizenNumber/departement")
    public ResponseEntity<Integer> getCitizenNumberOfDepartement(@RequestParam String name){

        List<Citoyen> citoyens = new ArrayList<>();
        Optional<Departement> departement = departementRepo.findByName(name);
        if (!departement.isPresent()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        for (Citoyen citoyen : citoyenRepo.findAll()){
            for (Habitat habitat : citoyen.getHabitats()){
                Secteur secteur = secteurRepo.findByName(habitat.getSecteur().getName()).get();
                Quartier quart = quartierRepo.findByName(secteur.getQuartier().getName()).get();
                Commune com = communeRepo.findByName(quart.getCommune().getName()).get();
                Ville vil = villeRepo.findByName(com.getVille().getName()).get();
                Departement depart = departementRepo.findByName(vil.getDepartement().getName()).get();
                if (depart.getName().equals(departement.get().getName())){
                    citoyens.add(citoyen);
                }
            }
        }
        return new ResponseEntity<>(citoyens.size(), HttpStatus.OK);
    }

    //Statistique: nombre d'habitant dans une region
    @GetMapping("/findCitizenNumber/region")
    public ResponseEntity<Integer> getCitizenNumberOfRegion(@RequestParam String name){

        List<Citoyen> citoyens = new ArrayList<>();
        Optional<Region> region = regionRepo.findByName(name);
        if (!region.isPresent()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        for (Citoyen citoyen : citoyenRepo.findAll()){
            for (Habitat habitat : citoyen.getHabitats()){
                Secteur secteur = secteurRepo.findByName(habitat.getSecteur().getName()).get();
                Quartier quart = quartierRepo.findByName(secteur.getQuartier().getName()).get();
                Commune com = communeRepo.findByName(quart.getCommune().getName()).get();
                Ville vil = villeRepo.findByName(com.getVille().getName()).get();
                Departement depart = departementRepo.findByName(vil.getDepartement().getName()).get();
                Region reg = regionRepo.findByName(depart.getRegion().getName()).get();
                if (reg.getName().equals(region.get().getName())){
                    citoyens.add(citoyen);
                }
            }
        }
        return new ResponseEntity<>(citoyens.size(), HttpStatus.OK);
    }

    //Modifier un citoyen
    @PutMapping("/updateCitoyen/{id}")
    public ResponseEntity<Citoyen> editCitoyen(@PathVariable String id, @RequestBody Citoyen citoyen){

        Citoyen citoyenFromDb = citoyenRepo.findById(id).orElse(null);

        if (citoyenFromDb == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        citoyenFromDb.setEmail(citoyen.getEmail());
        citoyenFromDb.setName(citoyen.getName());
        citoyenFromDb.setNationalite(citoyen.getNationalite());
        citoyenFromDb.setNumCni(citoyen.getNumCni());
        citoyenFromDb.setPhone(citoyen.getPhone());
        citoyenFromDb.setPrenom(citoyenFromDb.getPrenom());
        citoyenFromDb.setProfession(citoyen.getProfession());
        citoyenFromDb.setSexe(citoyen.getSexe());
        citoyenFromDb.setSituationMat(citoyen.getSituationMat());

        Citoyen citoyenUpdate = citoyenRepo.save(citoyenFromDb);
        return new ResponseEntity<>(citoyenUpdate, HttpStatus.OK);
    }

    //Supprimer un citoyen
    @DeleteMapping("/dropCitoyen/{id}")
    public String deleteCitoyen(@PathVariable String id){
        citoyenRepo.deleteById(id);
        return "Deleted with Successfully from database";
    }
}
