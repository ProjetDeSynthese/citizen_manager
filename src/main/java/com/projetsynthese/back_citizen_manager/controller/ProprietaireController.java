package com.projetsynthese.back_citizen_manager.controller;

import com.projetsynthese.back_citizen_manager.DTO.ProprietaireDTO;
import com.projetsynthese.back_citizen_manager.entity.Proprietaire;
import com.projetsynthese.back_citizen_manager.exeption.message.Message;
import com.projetsynthese.back_citizen_manager.services.ProprietaireService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/proprietaire/")
@CrossOrigin(origins = "*")
public class ProprietaireController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProprietaireService proprietaireService;

    @PostMapping()
    public ResponseEntity<Message> save(@RequestBody Proprietaire proprietaire){
        if (proprietaire == null){
            Message message = Message.builder()
                    .code(500).message("Entity is required")
                    .build();
            return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
        }
        this.proprietaireService.create(proprietaire);
        Message message = Message.builder()
                .code(201).message("Record Successfully")
                .build();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<ProprietaireDTO>> findAll(){
        return new ResponseEntity<>( this.proprietaireService.findAll()
                .stream()
                .map(proprietaire -> modelMapper.map(proprietaire,ProprietaireDTO.class))
                .collect(Collectors.toList()),HttpStatus.OK );

    }
    @GetMapping("{id}")
    public ResponseEntity<ProprietaireDTO> findById(@PathVariable String id){
        return new ResponseEntity<>(
                modelMapper.map(this.proprietaireService.findById(id),ProprietaireDTO.class),
                HttpStatus.OK);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Message>  deleteById(@PathVariable String id){
        try{
            this.proprietaireService.deleteById(id);
            Message message = Message.builder().message("Successfully").code(201).build();
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        catch (Exception e){
            Message message = Message.builder().message("Error").code(500).build();
            return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);

        }

    }

}
