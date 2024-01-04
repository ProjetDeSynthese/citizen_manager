package com.projetsynthese.back_citizen_manager.controller;

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
    @GetMapping("{code}")
    public ResponseEntity<VilleDTO> findById(@PathVariable String code){
        return new ResponseEntity<>(
                modelMapper.map(this.villeService.findByCode(code),VilleDTO.class),
                HttpStatus.OK);
    }
}
