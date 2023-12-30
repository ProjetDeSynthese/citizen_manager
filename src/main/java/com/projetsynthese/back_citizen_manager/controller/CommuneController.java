package com.projetsynthese.back_citizen_manager.controller;

import com.projetsynthese.back_citizen_manager.DTO.CommuneDTO;
import com.projetsynthese.back_citizen_manager.DTO.DepartementDTO;
import com.projetsynthese.back_citizen_manager.entity.Commune;
import com.projetsynthese.back_citizen_manager.entity.Departement;
import com.projetsynthese.back_citizen_manager.entity.Region;
import com.projetsynthese.back_citizen_manager.entity.Ville;
import com.projetsynthese.back_citizen_manager.exeption.message.Message;
import com.projetsynthese.back_citizen_manager.repository.CommuneRepository;
import com.projetsynthese.back_citizen_manager.repository.DepartementRepository;
import com.projetsynthese.back_citizen_manager.repository.RegionRepository;
import com.projetsynthese.back_citizen_manager.repository.VilleRepository;
import com.projetsynthese.back_citizen_manager.services.CommuneService;
import com.projetsynthese.back_citizen_manager.services.DepartementService;
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
@RequestMapping("/api/commune/")
public class CommuneController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CommuneService communeService;

    @PostMapping()
    public ResponseEntity<Message> save(@RequestBody Commune commune){
        if (commune == null){
            Message message = Message.builder()
                    .code(500).message("Entity is required")
                    .build();
            return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
        }
        this.communeService.create(commune);
        Message message = Message.builder()
                .code(201).message("Record Successfully")
                .build();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @GetMapping()
    public ResponseEntity<List<CommuneDTO>> findAll(){
        return new ResponseEntity<>( this.communeService.findAll()
                .stream()
                .map(commune -> modelMapper.map(commune,CommuneDTO.class))
                .collect(Collectors.toList()),HttpStatus.OK );

    }
    @GetMapping("{code}")
    public ResponseEntity<CommuneDTO> findById(@PathVariable String code){
        return new ResponseEntity<>(
                modelMapper.map(this.communeService.findByCode(code),CommuneDTO.class),
                HttpStatus.OK);
    }
}
