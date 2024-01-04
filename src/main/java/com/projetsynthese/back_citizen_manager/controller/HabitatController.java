package com.projetsynthese.back_citizen_manager.controller;

import com.projetsynthese.back_citizen_manager.DTO.DepartementDTO;
import com.projetsynthese.back_citizen_manager.DTO.HabitatDTO;
import com.projetsynthese.back_citizen_manager.entity.*;
import com.projetsynthese.back_citizen_manager.exeption.message.Message;
import com.projetsynthese.back_citizen_manager.repository.*;
import com.projetsynthese.back_citizen_manager.services.DepartementService;
import com.projetsynthese.back_citizen_manager.services.HabitatService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/habitat/")
@CrossOrigin(origins = "*")
public class HabitatController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private HabitatService habitatService;

    @PostMapping()
    public ResponseEntity<Message> save(@RequestBody Habitat habitat){
        if (habitat == null){
            Message message = Message.builder()
                    .code(500).message("Entity is required")
                    .build();
            return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
        }
        this.habitatService.create(habitat);
        Message message = Message.builder()
                .code(201).message("Record Successfully")
                .build();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @GetMapping()
    public ResponseEntity<List<HabitatDTO>> findAll(){
        return new ResponseEntity<>( this.habitatService.findAll()
                .stream()
                .map(habitat -> modelMapper.map(habitat,HabitatDTO.class))
                .collect(Collectors.toList()),HttpStatus.OK );

    }
    @GetMapping("{address}")
    public ResponseEntity<HabitatDTO> findById(@PathVariable String addresse){
        return new ResponseEntity<>(
                modelMapper.map(this.habitatService.findByAddress(addresse),HabitatDTO.class),
                HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Message>  deleteById(@PathVariable String id){
        try{
            this.habitatService.deleteById(id);
            Message message = Message.builder().message("Successfully").code(201).build();
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        catch (Exception e){
            Message message = Message.builder().message("Error").code(500).build();
            return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);

        }

    }
/*
    @Autowired
    private CitoyenRepo citoyenRepo;
    @Autowired
    private RegionRepository regionRepo;
    @Autowired
    private SecteurRepository secteurRepo;
    @Autowired
    private DepartementRepository departementRepo;
    @Autowired
    private VilleRepository villeRepo;
    @Autowired
    private QuartierRepository quartierRepo;
    @Autowired
    private CommuneRepository communeRepo;
    @Autowired
    private HabitatRepository habitatRepo;

    //Ajouter un habitat
    @PostMapping("/addHabitat")
    public ResponseEntity<Habitat> saveHabitat(@RequestBody Habitat habitat){

        if (habitat == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        Habitat saveHabitat = habitatRepo.save(habitat);
        return new ResponseEntity<>(saveHabitat, HttpStatus.OK);
    }

    //Liste des habitats
    @GetMapping("/findAllHabitat")
    public List<Habitat> getAllHabitat(){
        return habitatRepo.findAll();
    }

    //Rechercher un habitat par id
    @GetMapping("/findHabitatById/{id}")
    public ResponseEntity<Habitat> getHabitatById(@PathVariable String id){

        Habitat habitatFromDB = habitatRepo.findById(id).orElse(null);
        if (habitatFromDB == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(habitatFromDB, HttpStatus.OK);
    }

    //    Statistique: nombre d'habitat dans un secteur
    @GetMapping("/findHabitatNumber/secteur")
    public ResponseEntity<Integer> getHabitatNumberOfSecteur(@RequestParam String name){

        List<Habitat> habitats = new ArrayList<>();
        Optional<Secteur> secteur = secteurRepo.findByName(name);
        if (!secteur.isPresent()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        for (Habitat habitat : habitatRepo.findAll()){
            if (habitat.getSecteur().getName().equals(secteur.get().getName())){
                habitats.add(habitat);
            }
        }
        return new ResponseEntity<>(habitats.size(), HttpStatus.OK);
    }

    //    Statistique: nombre d'habitat dans un quartier
    @GetMapping("/findHabitatNumber/quartier")
    public ResponseEntity<Integer> getHabitatNumberOfQuartier(@RequestParam String name){

        List<Habitat> habitats = new ArrayList<>();
        Optional<Quartier> quartier = quartierRepo.findByName(name);
        if (!quartier.isPresent()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        for (Habitat habitat : habitatRepo.findAll()){
            Secteur secteur = secteurRepo.findByName(habitat.getSecteur().getName()).get();
            Quartier quart = quartierRepo.findByName(secteur.getQuartier().getName()).get();
            if (quart.getName().equals(quartier.get().getName())){
                habitats.add(habitat);
            }
        }
        return new ResponseEntity<>(habitats.size(), HttpStatus.OK);
    }

    //    Statistique: nombre d'habitat dans une commune
    @GetMapping("/findHabitatNumber/commune")
    public ResponseEntity<Integer> getHabitatNumberOfCommune(@RequestParam String name){

        List<Habitat> habitats = new ArrayList<>();
        Optional<Commune> commune = communeRepo.findByName(name);
        if (!commune.isPresent()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        for (Habitat habitat : habitatRepo.findAll()){
            Secteur secteur = secteurRepo.findByName(habitat.getSecteur().getName()).get();
            Quartier quart = quartierRepo.findByName(secteur.getQuartier().getName()).get();
            Commune com = communeRepo.findByName(quart.getCommune().getName()).get();
            if (com.getName().equals(commune.get().getName())){
                habitats.add(habitat);
            }
        }
        return new ResponseEntity<>(habitats.size(), HttpStatus.OK);
    }

    //    Statistique: nombre d'habitat dans une ville
    @GetMapping("/findHabitatNumber/ville")
    public ResponseEntity<Integer> getHabitatNumberOfVille(@RequestParam String name){

        List<Habitat> habitats = new ArrayList<>();
        Optional<Ville> ville = villeRepo.findByName(name);
        if (!ville.isPresent()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        for (Habitat habitat : habitatRepo.findAll()){
            Secteur secteur = secteurRepo.findByName(habitat.getSecteur().getName()).get();
            Quartier quart = quartierRepo.findByName(secteur.getQuartier().getName()).get();
            Commune com = communeRepo.findByName(quart.getCommune().getName()).get();
            Ville vil = villeRepo.findByName(com.getVille().getName()).get();
            if (vil.getName().equals(ville.get().getName())){
                habitats.add(habitat);
            }
        }
        return new ResponseEntity<>(habitats.size(), HttpStatus.OK);
    }

    //    Statistique: nombre d'habitat dans un departement
    @GetMapping("/findHabitatNumber/departement")
    public ResponseEntity<Integer> getHabitatNumberOfDepartement(@RequestParam String name){

        List<Habitat> habitats = new ArrayList<>();
        Optional<Departement> departement = departementRepo.findByName(name);
        if (!departement.isPresent()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        for (Habitat habitat : habitatRepo.findAll()){
            Secteur secteur = secteurRepo.findByName(habitat.getSecteur().getName()).get();
            Quartier quart = quartierRepo.findByName(secteur.getQuartier().getName()).get();
            Commune com = communeRepo.findByName(quart.getCommune().getName()).get();
            Ville vil = villeRepo.findByName(com.getVille().getName()).get();
            Departement depart = departementRepo.findByName(vil.getDepartement().getName()).get();
            if (depart.getName().equals(departement.get().getName())){
                habitats.add(habitat);
            }
        }
        return new ResponseEntity<>(habitats.size(), HttpStatus.OK);
    }

    //    Statistique: nombre d'habitat dans une region
    @GetMapping("/findHabitatNumber/region")
    public ResponseEntity<Integer> getHabitatNumberOfRegion(@RequestParam String name){

        List<Habitat> habitats = new ArrayList<>();
        Optional<Region> region = regionRepo.findByName(name);
        if (!region.isPresent()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        for (Habitat habitat : habitatRepo.findAll()){
            Secteur secteur = secteurRepo.findByName(habitat.getSecteur().getName()).get();
            Quartier quart = quartierRepo.findByName(secteur.getQuartier().getName()).get();
            Commune com = communeRepo.findByName(quart.getCommune().getName()).get();
            Ville vil = villeRepo.findByName(com.getVille().getName()).get();
            Departement depart = departementRepo.findByName(vil.getDepartement().getName()).get();
            Region reg = regionRepo.findByName(depart.getRegion().getName()).get();
            if (reg.getName().equals(region.get().getName())){
                habitats.add(habitat);
            }
        }
        return new ResponseEntity<>(habitats.size(), HttpStatus.OK);
    }

    //Liste des habitats libre
    @GetMapping("/findHabitatLibre")
    public ResponseEntity<List<Habitat>> getAllHabitatLibre(){

        List<Habitat> habitats = new ArrayList<>();
        for (Habitat habitat : habitatRepo.findAll()){
            if (habitat.isLibre() == true){
                habitats.add(habitat);
            }
        }
        if (habitats == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(habitats, HttpStatus.OK);
    }

//    Liste des habitats d'un secteur
    @GetMapping("/findHabitatBySecteur")
    public ResponseEntity<List<Habitat>> getHabitatBySecteur(@RequestParam String name){

        List<Habitat> habitats = new ArrayList<>();
        Optional<Secteur> secteur = secteurRepo.findByName(name);
        if (!secteur.isPresent()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        for (Habitat habitat : habitatRepo.findAll()){
            if (habitat.getSecteur().getName().equals(secteur.get().getName())){
                habitats.add(habitat);
            }
        }
        return new ResponseEntity<>(habitats, HttpStatus.OK);
    }

//    Liste des habitats libre d'un secteur
    @GetMapping("/findHabitatLibreBySecteur")
    public ResponseEntity<List<Habitat>> getHabitatLibreBySecteur(@RequestParam String name){

        List<Habitat> habitats = new ArrayList<>();
        Optional<Secteur> secteur = secteurRepo.findByName(name);
        if (!secteur.isPresent()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        for (Habitat habitat : habitatRepo.findAll()){
            if ((habitat.getSecteur().getName().equals(secteur.get().getName())) && (habitat.isLibre() == true)){
                habitats.add(habitat);
            }
        }
        return new ResponseEntity<>(habitats, HttpStatus.OK);
    }

    //    Liste des habitats d'un quartier
    @GetMapping("/findHabitatByQuartier")
    public ResponseEntity<List<Habitat>> getHabitatByQuartier(@RequestParam String name){

        List<Habitat> habitats = new ArrayList<>();
        Optional<Quartier> quartier = quartierRepo.findByName(name);
        if (!quartier.isPresent()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        for (Habitat habitat : habitatRepo.findAll()){
            Secteur secteur = secteurRepo.findByName(habitat.getSecteur().getName()).get();
            Quartier quart = quartierRepo.findByName(secteur.getQuartier().getName()).get();
            if (quart.getName().equals(quartier.get().getName())){
                habitats.add(habitat);
            }
        }
        return new ResponseEntity<>(habitats, HttpStatus.OK);
    }

    //    Liste des habitats libre d'un quartier
    @GetMapping("/findHabitatLibreByQuartier")
    public ResponseEntity<List<Habitat>> getHabitatLibreByQuartier(@RequestParam String name){

        List<Habitat> habitats = new ArrayList<>();
        Optional<Quartier> quartier = quartierRepo.findByName(name);
        if (!quartier.isPresent()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        for (Habitat habitat : habitatRepo.findAll()){
            Secteur secteur = secteurRepo.findByName(habitat.getSecteur().getName()).get();
            Quartier quart = quartierRepo.findByName(secteur.getQuartier().getName()).get();
            if ((quart.getName().equals(quartier.get().getName())) && (habitat.isLibre() == true)){
                habitats.add(habitat);
            }
        }
        return new ResponseEntity<>(habitats, HttpStatus.OK);
    }

    //    Liste des habitats d'une commune
    @GetMapping("/findHabitatByCommune")
    public ResponseEntity<List<Habitat>> getHabitatByCommune(@RequestParam String name){

        List<Habitat> habitats = new ArrayList<>();
        Optional<Commune> commune = communeRepo.findByName(name);
        if (!commune.isPresent()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        for (Habitat habitat : habitatRepo.findAll()){
            Secteur secteur = secteurRepo.findByName(habitat.getSecteur().getName()).get();
            Quartier quart = quartierRepo.findByName(secteur.getQuartier().getName()).get();
            Commune com = communeRepo.findByName(quart.getCommune().getName()).get();
            if (com.getName().equals(commune.get().getName())){
                habitats.add(habitat);
            }
        }
        return new ResponseEntity<>(habitats, HttpStatus.OK);
    }

    //    Liste des habitats libre d'un commune
    @GetMapping("/findHabitatLibreByCommune")
    public ResponseEntity<List<Habitat>> getHabitatLibreByCommune(@RequestParam String name){

        List<Habitat> habitats = new ArrayList<>();
        Optional<Commune> commune = communeRepo.findByName(name);
        if (!commune.isPresent()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        for (Habitat habitat : habitatRepo.findAll()){
            Secteur secteur = secteurRepo.findByName(habitat.getSecteur().getName()).get();
            Quartier quart = quartierRepo.findByName(secteur.getQuartier().getName()).get();
            Commune com = communeRepo.findByName(quart.getCommune().getName()).get();
            if ((com.getName().equals(commune.get().getName())) && (habitat.isLibre() == true)){
                habitats.add(habitat);
            }
        }
        return new ResponseEntity<>(habitats, HttpStatus.OK);
    }

    //    Liste des habitats d'une ville
    @GetMapping("/findHabitatByVille")
    public ResponseEntity<List<Habitat>> getHabitatByVille(@RequestParam String name){

        List<Habitat> habitats = new ArrayList<>();
        Optional<Ville> ville = villeRepo.findByName(name);
        if (!ville.isPresent()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        for (Habitat habitat : habitatRepo.findAll()){
            Secteur secteur = secteurRepo.findByName(habitat.getSecteur().getName()).get();
            Quartier quart = quartierRepo.findByName(secteur.getQuartier().getName()).get();
            Commune com = communeRepo.findByName(quart.getCommune().getName()).get();
            Ville vil = villeRepo.findByName(com.getVille().getName()).get();
            if (vil.getName().equals(ville.get().getName())){
                habitats.add(habitat);
            }
        }
        return new ResponseEntity<>(habitats, HttpStatus.OK);
    }

    //    Liste des habitats libre d'un ville
    @GetMapping("/findHabitatLibreByVille")
    public ResponseEntity<List<Habitat>> getHabitatLibreByVille(@RequestParam String name){

        List<Habitat> habitats = new ArrayList<>();
        Optional<Ville> ville = villeRepo.findByName(name);
        if (!ville.isPresent()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        for (Habitat habitat : habitatRepo.findAll()){
            Secteur secteur = secteurRepo.findByName(habitat.getSecteur().getName()).get();
            Quartier quart = quartierRepo.findByName(secteur.getQuartier().getName()).get();
            Commune com = communeRepo.findByName(quart.getCommune().getName()).get();
            Ville vil = villeRepo.findByName(com.getVille().getName()).get();
            if ((vil.getName().equals(ville.get().getName())) && (habitat.isLibre() == true)){
                habitats.add(habitat);
            }
        }
        return new ResponseEntity<>(habitats, HttpStatus.OK);
    }

    //    Liste des habitats d'un departement
    @GetMapping("/findHabitatByDepartement")
    public ResponseEntity<List<Habitat>> getHabitatByDepartement(@RequestParam String name){

        List<Habitat> habitats = new ArrayList<>();
        Optional<Departement> departement = departementRepo.findByName(name);
        if (!departement.isPresent()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        for (Habitat habitat : habitatRepo.findAll()){
            Secteur secteur = secteurRepo.findByName(habitat.getSecteur().getName()).get();
            Quartier quart = quartierRepo.findByName(secteur.getQuartier().getName()).get();
            Commune com = communeRepo.findByName(quart.getCommune().getName()).get();
            Ville vil = villeRepo.findByName(com.getVille().getName()).get();
            Departement depart = departementRepo.findByName(vil.getDepartement().getName()).get();
            if (depart.getName().equals(departement.get().getName())){
                habitats.add(habitat);
            }
        }
        return new ResponseEntity<>(habitats, HttpStatus.OK);
    }

    //    Liste des habitats libre d'un departement
    @GetMapping("/findHabitatLibreByDepartement")
    public ResponseEntity<List<Habitat>> getHabitatLibreByDepartement(@RequestParam String name){

        List<Habitat> habitats = new ArrayList<>();
        Optional<Departement> departement = departementRepo.findByName(name);
        if (!departement.isPresent()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        for (Habitat habitat : habitatRepo.findAll()){
            Secteur secteur = secteurRepo.findByName(habitat.getSecteur().getName()).get();
            Quartier quart = quartierRepo.findByName(secteur.getQuartier().getName()).get();
            Commune com = communeRepo.findByName(quart.getCommune().getName()).get();
            Ville vil = villeRepo.findByName(com.getVille().getName()).get();
            Departement depart = departementRepo.findByName(vil.getDepartement().getName()).get();
            if ((depart.getName().equals(departement.get().getName())) && (habitat.isLibre() == true)){
                habitats.add(habitat);
            }
        }
        return new ResponseEntity<>(habitats, HttpStatus.OK);
    }

    //    Liste des habitats d'une region
    @GetMapping("/findHabitatByRegion")
    public ResponseEntity<List<Habitat>> getHabitatByRegion(@RequestParam String name){

        List<Habitat> habitats = new ArrayList<>();
        Optional<Region> region = regionRepo.findByName(name);
        if (!region.isPresent()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        for (Habitat habitat : habitatRepo.findAll()){
            Secteur secteur = secteurRepo.findByName(habitat.getSecteur().getName()).get();
            Quartier quart = quartierRepo.findByName(secteur.getQuartier().getName()).get();
            Commune com = communeRepo.findByName(quart.getCommune().getName()).get();
            Ville vil = villeRepo.findByName(com.getVille().getName()).get();
            Departement depart = departementRepo.findByName(vil.getDepartement().getName()).get();
            Region reg = regionRepo.findByName(depart.getRegion().getName()).get();
            if (reg.getName().equals(region.get().getName())){
                habitats.add(habitat);
            }
        }
        return new ResponseEntity<>(habitats, HttpStatus.OK);
    }

    //    Liste des habitats libre d'une region
    @GetMapping("/findHabitatLibreByRegion")
    public ResponseEntity<List<Habitat>> getHabitatLibreByRegion(@RequestParam String name){

        List<Habitat> habitats = new ArrayList<>();
        Optional<Region> region = regionRepo.findByName(name);
        if (!region.isPresent()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        for (Habitat habitat : habitatRepo.findAll()){
            Secteur secteur = secteurRepo.findByName(habitat.getSecteur().getName()).get();
            Quartier quart = quartierRepo.findByName(secteur.getQuartier().getName()).get();
            Commune com = communeRepo.findByName(quart.getCommune().getName()).get();
            Ville vil = villeRepo.findByName(com.getVille().getName()).get();
            Departement depart = departementRepo.findByName(vil.getDepartement().getName()).get();
            Region reg = regionRepo.findByName(depart.getRegion().getName()).get();
            if ((reg.getName().equals(region.get().getName())) && (habitat.isLibre() == true)){
                habitats.add(habitat);
            }
        }
        return new ResponseEntity<>(habitats, HttpStatus.OK);
    }

    //Modifier un habitat
    @PutMapping("/updateHabitat/{id}")
    public ResponseEntity<Habitat> editHabitat(@PathVariable String id, @RequestBody Habitat habitat){

        Habitat habitatFromDB = habitatRepo.findById(id).orElse(null);
        if (habitatFromDB == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        habitatFromDB.setAdresse(habitat.getAdresse());
        habitatFromDB.setSuperficie(habitat.getSuperficie());
        habitatFromDB.setAnneeConstruct(habitat.getAnneeConstruct());
        habitatFromDB.setEtage(habitat.getEtage());
        habitatFromDB.setNombreChambre(habitat.getNombreChambre());
        habitatFromDB.setParking(habitat.getParking());
        habitatFromDB.setProprietaire(habitat.getProprietaire());
        habitatFromDB.setLibre(habitat.isLibre());

        Habitat updateHabitat = habitatRepo.save(habitatFromDB);
        return new ResponseEntity<>(updateHabitat, HttpStatus.OK);
    }

    //Supprimer un habitat
    @DeleteMapping("/dropHabitat/{id}")
    public String deleteHabitat(@PathVariable String id){

        habitatRepo.deleteById(id);
        return "Deleted with Successfully from database";
    }

 */
}
