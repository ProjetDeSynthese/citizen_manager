package com.projetsynthese.back_citizen_manager.controller;

import com.projetsynthese.back_citizen_manager.DTO.HabitatDTO;
import com.projetsynthese.back_citizen_manager.DTO.QuartierDTO;
import com.projetsynthese.back_citizen_manager.entity.*;
import com.projetsynthese.back_citizen_manager.exeption.message.Message;
import com.projetsynthese.back_citizen_manager.repository.*;
import com.projetsynthese.back_citizen_manager.services.HabitatService;
import com.projetsynthese.back_citizen_manager.services.QuartierService;
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
@RequestMapping("/api/quartier/")
@CrossOrigin(origins = "*")
public class QuartierController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private QuartierService quartierService;

    @PostMapping()
    public ResponseEntity<Message> save(@RequestBody Quartier quartier){
        if (quartier == null){
            Message message = Message.builder()
                    .code(500).message("Entity is required")
                    .build();
            return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
        }
        this.quartierService.create(quartier);
        Message message = Message.builder()
                .code(201).message("Record Successfully")
                .build();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @GetMapping()
    public ResponseEntity<List<QuartierDTO>> findAll(){
        return new ResponseEntity<>( this.quartierService.findAll()
                .stream()
                .map(quartier -> modelMapper.map(quartier,QuartierDTO.class))
                .collect(Collectors.toList()),HttpStatus.OK );

    }
    @GetMapping("{id}")
    public ResponseEntity<QuartierDTO> findById(@PathVariable String id){
        return new ResponseEntity<>(
                modelMapper.map(this.quartierService.findByCode(id),QuartierDTO.class),
                HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Message>  deleteById(@PathVariable String id){
        try{
            this.quartierService.deleteById(id);
            Message message = Message.builder().message("Successfully").code(201).build();
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        catch (Exception e){
            Message message = Message.builder().message("Error").code(500).build();
            return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);

        }

    }

    /*@Autowired
    private QuartierRepository quartierRepo;
    @Autowired
    private DepartementRepository departementRepo;
    @Autowired
    private VilleRepository villeRepo;
    @Autowired
    private RegionRepository regionRepo;
    @Autowired
    private CommuneRepository communeRepo;

    @PostMapping("/addQuartier")
    public ResponseEntity<Quartier> saveQuartier(@RequestBody Quartier quartier){

        if (quartier == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(quartierRepo.save(quartier), HttpStatus.OK);
    }

//    Liste des quartiers par commune
    @GetMapping("/findQuartierByCommune")
    public ResponseEntity<List<Quartier>> getQuartierByCommune(@RequestParam String nameCom){

        List<Quartier> quartiers = new ArrayList<>();
        Optional<Commune> commune = communeRepo.findByName(nameCom);
        if (!commune.isPresent()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        for (Quartier quartier : quartierRepo.findAll()){
            if (quartier.getCommune().getName().equals(commune.get().getName())){
                quartiers.add(quartier);
            }
        }
        return new ResponseEntity<>(quartiers, HttpStatus.OK);
    }

//    Liste des quartiers par ville
    @GetMapping("/findQuartierByVille")
    public ResponseEntity<List<Quartier>> getQuartierByVille(@RequestParam String nameVille){

        List<Quartier> quartiers = new ArrayList<>();
        Optional<Ville> ville = villeRepo.findByName(nameVille);
        if (!ville.isPresent()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        for (Quartier quartier : quartierRepo.findAll()){
            Commune commune = communeRepo.findByName(quartier.getCommune().getName()).get();
            Ville vil = villeRepo.findByName(commune.getVille().getName()).get();
            if (vil.getName().equals(ville.get().getName())){
                quartiers.add(quartier);
            }
        }
        return new ResponseEntity<>(quartiers, HttpStatus.OK);
    }

    //    Liste des quartiers par departement
    @GetMapping("/findQuartierByDepartement")
    public ResponseEntity<List<Quartier>> getQuartierByDepart(@RequestParam String nameDepart){

        List<Quartier> quartiers = new ArrayList<>();
        Optional<Departement> departement = departementRepo.findByName(nameDepart);
        if (!departement.isPresent()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        for (Quartier quartier : quartierRepo.findAll()){
            Commune commune = communeRepo.findByName(quartier.getCommune().getName()).get();
            Ville vil = villeRepo.findByName(commune.getVille().getName()).get();
            Departement depart = departementRepo.findByName(vil.getDepartement().getName()).get();
            if (depart.getName().equals(departement.get().getName())){
                quartiers.add(quartier);
            }
        }
        return new ResponseEntity<>(quartiers, HttpStatus.OK);
    }

    //    Liste des quartiers par region
    @GetMapping("/findQuartierByRegion")
    public ResponseEntity<List<Quartier>> getQuartierByRegion(@RequestParam String nameRegion){

        List<Quartier> quartiers = new ArrayList<>();
        Optional<Region> region = regionRepo.findByName(nameRegion);
        if (!region.isPresent()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        for (Quartier quartier : quartierRepo.findAll()){
            Commune commune = communeRepo.findByName(quartier.getCommune().getName()).get();
            Ville vil = villeRepo.findByName(commune.getVille().getName()).get();
            Departement depart = departementRepo.findByName(vil.getDepartement().getName()).get();
            Region reg = regionRepo.findByName(depart.getRegion().getName()).get();
            if (reg.getName().equals(region.get().getName())){
                quartiers.add(quartier);
            }
        }
        return new ResponseEntity<>(quartiers, HttpStatus.OK);
    }

    @GetMapping("/findQuartierById/{id}")
    public ResponseEntity<Quartier> getQuartierById(@PathVariable("id") String id){

        Quartier quartier = quartierRepo.findById(id).orElse(null);
        if (quartier == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(quartier, HttpStatus.OK);
    }

    @PutMapping("/updateQuartier/{id}")
    public ResponseEntity<Quartier> editQuartier(@PathVariable("id") String id, @RequestBody Quartier quartier){

        Quartier quart = quartierRepo.findById(id).orElse(null);
        if (quart == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        quart.setCode(quartier.getCode());
        quart.setName(quartier.getName());
        quart.setSuperficie(quartier.getSuperficie());

        return new ResponseEntity<>(quartierRepo.save(quart), HttpStatus.OK);
    }

    @DeleteMapping("/dropQuartier/{id}")
    public String deleteQuartier(@PathVariable("id") String id){

        quartierRepo.deleteById(id);
        return "Deleted with Successfully from database";
    }

     */
}
