package com.projetsynthese.back_citizen_manager.controller;

import com.projetsynthese.back_citizen_manager.DTO.MaisonDTO;
import com.projetsynthese.back_citizen_manager.entity.*;
import com.projetsynthese.back_citizen_manager.exeption.message.Message;
import com.projetsynthese.back_citizen_manager.services.MaisonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/maison/")
@CrossOrigin(origins = "*")
public class MaisonController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MaisonService maisonService;

    @PostMapping()
    public ResponseEntity<Message> save(@RequestBody Maison maison){
        if (maison == null){
            Message message = Message.builder()
                    .code(500).message("Entity is required")
                    .build();
            return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
        }
        this.maisonService.create(maison);
        Message message = Message.builder()
                .code(201).message("Record Successfully")
                .build();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @GetMapping()
    public ResponseEntity<List<MaisonDTO>> findAll(){
        return new ResponseEntity<>( this.maisonService.findAll()
                .stream()
                .map(maison -> modelMapper.map(maison, MaisonDTO.class))
                .collect(Collectors.toList()),HttpStatus.OK );

    }
    @GetMapping("{id}")
    public ResponseEntity<MaisonDTO> findById(@PathVariable String id){
        return new ResponseEntity<>(
                modelMapper.map(this.maisonService.findById(id), MaisonDTO.class),
                HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Message>  deleteById(@PathVariable String id){
        try{
            this.maisonService.deleteById(id);
            Message message = Message.builder().message("Successfully").code(201).build();
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        catch (Exception e){
            Message message = Message.builder().message("Error").code(500).build();
            return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);

        }

    }
}
