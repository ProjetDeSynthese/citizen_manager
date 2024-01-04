package com.projetsynthese.back_citizen_manager.controller;

import com.projetsynthese.back_citizen_manager.DTO.DepartementDTO;
import com.projetsynthese.back_citizen_manager.entity.Departement;
import com.projetsynthese.back_citizen_manager.exeption.message.Message;
import com.projetsynthese.back_citizen_manager.services.DepartementService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/departement/")
@CrossOrigin(origins = "*")
public class DepartementController {


    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private DepartementService departementService;

    @PostMapping()
    public ResponseEntity<Message> save(@RequestBody Departement departement){
        if (departement == null){
            Message message = Message.builder()
                    .code(500).message("Entity is required")
                    .build();
            return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
        }
        this.departementService.create(departement);
        Message message = Message.builder()
                .code(201).message("Record Successfully")
                .build();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @GetMapping()
    public ResponseEntity<List<DepartementDTO>> findAll(){
        return new ResponseEntity<>( this.departementService.findAll()
                .stream()
                .map(departement -> modelMapper.map(departement,DepartementDTO.class))
                .collect(Collectors.toList()),HttpStatus.OK );

    }
    @GetMapping("{code}")
    public ResponseEntity<DepartementDTO> findById(@PathVariable String code){
        return new ResponseEntity<>(
                modelMapper.map(this.departementService.findByCode(code),DepartementDTO.class),
                HttpStatus.OK);
    }
}
