package com.projetsynthese.back_citizen_manager.controller;

import com.projetsynthese.back_citizen_manager.DTO.QuartierDTO;
import com.projetsynthese.back_citizen_manager.entity.*;
import com.projetsynthese.back_citizen_manager.exeption.message.Message;
import com.projetsynthese.back_citizen_manager.services.QuartierService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
                modelMapper.map(this.quartierService.findById(id),QuartierDTO.class),
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
    @GetMapping("findByCommune/{commune}")
    public ResponseEntity<List<QuartierDTO>> findByCommune(@PathVariable String commune){
        return new ResponseEntity<>( this.quartierService.findByCommune(commune)
                .stream()
                .map(communee -> modelMapper.map(communee,QuartierDTO.class))
                .collect(Collectors.toList()),HttpStatus.OK );
    }
}
