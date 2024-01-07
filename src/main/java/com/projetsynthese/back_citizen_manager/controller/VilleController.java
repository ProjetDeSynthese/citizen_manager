package com.projetsynthese.back_citizen_manager.controller;

import com.projetsynthese.back_citizen_manager.DTO.DepartementDTO;
import com.projetsynthese.back_citizen_manager.DTO.VilleDTO;
import com.projetsynthese.back_citizen_manager.entity.*;
import com.projetsynthese.back_citizen_manager.exeption.message.Message;
import com.projetsynthese.back_citizen_manager.services.VilleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/ville/")
@CrossOrigin(origins = "*")
public class VilleController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private VilleService villeService;

    @PostMapping()
    public ResponseEntity<Message> save(@RequestBody Ville ville){
        if (ville == null){
            Message message = Message.builder()
                    .code(500).message("Entity is required")
                    .build();
            return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
        }
        this.villeService.create(ville);
        Message message = Message.builder()
                .code(201).message("Record Successfully")
                .build();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @GetMapping()
    public ResponseEntity<List<VilleDTO>> findAll(){
        return new ResponseEntity<>( this.villeService.findAll()
                .stream()
                .map(ville -> modelMapper.map(ville,VilleDTO.class))
                .collect(Collectors.toList()),HttpStatus.OK );

    }
    @GetMapping("{id}")
    public ResponseEntity<VilleDTO> findById(@PathVariable String id){
        return new ResponseEntity<>(
                modelMapper.map(this.villeService.findById(id),VilleDTO.class),
                HttpStatus.OK);
    }
    @GetMapping("findByCode/{code}")
    public ResponseEntity<VilleDTO> findByCode(@PathVariable String code){
        return new ResponseEntity<>(
                modelMapper.map(this.villeService.findByCode(code),VilleDTO.class),
                HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Message>  deleteById(@PathVariable String id){
        try{
            this.villeService.deleteById(id);
           Message message = Message.builder().message("Successfully").code(201).build();
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        catch (Exception e){
            Message message = Message.builder().message("Error").code(500).build();
            return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);

        }

    }

    @GetMapping("findByDepartement/{departement}")
    public ResponseEntity<List<VilleDTO>> findByDepartement(@PathVariable String departement){
        return new ResponseEntity<>( this.villeService.findByDepartement(departement)
                .stream()
                .map(ville -> modelMapper.map(ville,VilleDTO.class))
                .collect(Collectors.toList()),HttpStatus.OK );
    }
}
