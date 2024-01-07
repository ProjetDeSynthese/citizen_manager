package com.projetsynthese.back_citizen_manager.controller;

import com.projetsynthese.back_citizen_manager.DTO.TypeHabitatDTO;
import com.projetsynthese.back_citizen_manager.DTO.VilleDTO;
import com.projetsynthese.back_citizen_manager.entity.TypeHabitat;
import com.projetsynthese.back_citizen_manager.entity.Ville;
import com.projetsynthese.back_citizen_manager.exeption.message.Message;
import com.projetsynthese.back_citizen_manager.services.TypeHabitatService;
import com.projetsynthese.back_citizen_manager.services.VilleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/typeHabitat/")
@CrossOrigin(origins = "*")
public class TypeHabitatController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TypeHabitatService typeHabitatService;

    @PostMapping()
    public ResponseEntity<Message> save(@RequestBody TypeHabitat typeHabitat){
        if (typeHabitat == null){
            Message message = Message.builder()
                    .code(500).message("Entity is required")
                    .build();
            return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
        }
        this.typeHabitatService.create(typeHabitat);
        Message message = Message.builder()
                .code(201).message("Record Successfully")
                .build();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @GetMapping()
    public ResponseEntity<List<TypeHabitatDTO>> findAll(){
        return new ResponseEntity<>( this.typeHabitatService.findAll()
                .stream()
                .map(ville -> modelMapper.map(ville,TypeHabitatDTO.class))
                .collect(Collectors.toList()),HttpStatus.OK );

    }
    @GetMapping("{id}")
    public ResponseEntity<TypeHabitatDTO> findById(@PathVariable String id){
        return new ResponseEntity<>(
                modelMapper.map(this.typeHabitatService.findById(id),TypeHabitatDTO.class),
                HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Message>  deleteById(@PathVariable String id){
        try{
            this.typeHabitatService.deleteById(id);
            Message message = Message.builder().message("Successfully").code(201).build();
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        catch (Exception e){
            Message message = Message.builder().message("Error").code(500).build();
            return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);

        }

    }
}
