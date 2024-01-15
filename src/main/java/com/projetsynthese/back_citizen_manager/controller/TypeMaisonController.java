package com.projetsynthese.back_citizen_manager.controller;

import com.projetsynthese.back_citizen_manager.DTO.TypeMaisonDTO;
import com.projetsynthese.back_citizen_manager.entity.TypeMaison;
import com.projetsynthese.back_citizen_manager.exeption.message.Message;
import com.projetsynthese.back_citizen_manager.services.TypeMaisonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/typeMaison/")
@CrossOrigin(origins = "*")
public class TypeMaisonController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TypeMaisonService typeMaisonService;

    @PostMapping()
    public ResponseEntity<Message> save(@RequestBody TypeMaison typeMaison){
        if (typeMaison == null){
            Message message = Message.builder()
                    .code(500).message("Entity is required")
                    .build();
            return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
        }
        this.typeMaisonService.create(typeMaison);
        Message message = Message.builder()
                .code(201).message("Record Successfully")
                .build();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @GetMapping()
    public ResponseEntity<List<TypeMaisonDTO>> findAll(){
        return new ResponseEntity<>( this.typeMaisonService.findAll()
                .stream()
                .map(typeMaison -> modelMapper.map(typeMaison, TypeMaisonDTO.class))
                .collect(Collectors.toList()),HttpStatus.OK );

    }
    @GetMapping("{id}")
    public ResponseEntity<TypeMaisonDTO> findById(@PathVariable String id){
        return new ResponseEntity<>(
                modelMapper.map(this.typeMaisonService.findById(id), TypeMaisonDTO.class),
                HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Message>  deleteById(@PathVariable String id){
        try{
            this.typeMaisonService.deleteById(id);
            Message message = Message.builder().message("Successfully").code(201).build();
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        catch (Exception e){
            Message message = Message.builder().message("Error").code(500).build();
            return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);

        }

    }
}
