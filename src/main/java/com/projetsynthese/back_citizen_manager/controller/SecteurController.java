package com.projetsynthese.back_citizen_manager.controller;

import com.projetsynthese.back_citizen_manager.DTO.QuartierDTO;
import com.projetsynthese.back_citizen_manager.DTO.SecteurDTO;
import com.projetsynthese.back_citizen_manager.entity.*;
import com.projetsynthese.back_citizen_manager.exeption.message.Message;
import com.projetsynthese.back_citizen_manager.repository.*;
import com.projetsynthese.back_citizen_manager.services.QuartierService;
import com.projetsynthese.back_citizen_manager.services.SecteurService;
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
@RequestMapping("/api/secteur/")
@CrossOrigin(origins = "*")
public class SecteurController {


    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SecteurService secteurService;

    @PostMapping()
    public ResponseEntity<Message> save(@RequestBody Secteur secteur){
        if (secteur == null){
            Message message = Message.builder()
                    .code(500).message("Entity is required")
                    .build();
            return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
        }
        this.secteurService.create(secteur);
        Message message = Message.builder()
                .code(201).message("Record Successfully")
                .build();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @GetMapping()
    public ResponseEntity<List<SecteurDTO>> findAll(){
        return new ResponseEntity<>( this.secteurService.findAll()
                .stream()
                .map(secteur -> modelMapper.map(secteur,SecteurDTO.class))
                .collect(Collectors.toList()),HttpStatus.OK );

    }
    @GetMapping("{id}")
    public ResponseEntity<SecteurDTO> findById(@PathVariable String id){
        return new ResponseEntity<>(
                modelMapper.map(this.secteurService.findById(id),SecteurDTO.class),
                HttpStatus.OK);
    }
    @GetMapping("findByCode/{code}")
    public ResponseEntity<SecteurDTO> findByCode(@PathVariable String code){
        return new ResponseEntity<>(
                modelMapper.map(this.secteurService.findByCode(code),SecteurDTO.class),
                HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Message>  deleteById(@PathVariable String id){
        try{
            this.secteurService.deleteById(id);
            Message message = Message.builder().message("Successfully").code(201).build();
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        catch (Exception e){
            Message message = Message.builder().message("Error").code(500).build();
            return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);

        }

    }
    @GetMapping("findByQuartier/{quartier_id}")
    public ResponseEntity<List<SecteurDTO>> findByQuartier(@PathVariable String quartier_id){
        return new ResponseEntity<>( this.secteurService.findByQuartier(quartier_id)
                .stream()
                .map(secteur -> modelMapper.map(secteur,SecteurDTO.class))
                .collect(Collectors.toList()),HttpStatus.OK );
    }
}
