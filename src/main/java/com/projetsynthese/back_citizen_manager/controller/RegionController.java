package com.projetsynthese.back_citizen_manager.controller;

import com.projetsynthese.back_citizen_manager.DTO.RegionDTO;
import com.projetsynthese.back_citizen_manager.entity.Region;
import com.projetsynthese.back_citizen_manager.exeption.message.Message;
import com.projetsynthese.back_citizen_manager.repository.RegionRepository;
import com.projetsynthese.back_citizen_manager.services.RegionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/region/")
public class RegionController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RegionService regionService;

    @PostMapping()
    public ResponseEntity<Message> saveRegion(@RequestBody Region region){
        if (region == null){
            Message message = Message.builder()
                    .code(500).message("Entity is required")
                    .build();
            return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
        }
        this.regionService.create(region);
        Message message = Message.builder()
                .code(201).message("Record Successfully")
                .build();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @GetMapping()
    public ResponseEntity<List<RegionDTO>> findAll(){
        return new ResponseEntity<>( this.regionService.findAll()
                .stream()
                .map(region -> modelMapper.map(region,RegionDTO.class))
                .collect(Collectors.toList()),HttpStatus.OK );

    }
    @GetMapping("{code}")
    public ResponseEntity<RegionDTO> findById(@PathVariable String code){
        return new ResponseEntity<>(
                modelMapper.map(this.regionService.findByCode(code),RegionDTO.class),
                HttpStatus.OK);
    }

    /*@PutMapping("/updateRegion/{id}")
    public ResponseEntity<Region> editRegion(@PathVariable("id") String id, @RequestBody Region region){

        Region reg = regionRepo.findById(id).orElse(null);
        if (reg == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        reg.setName(region.getName());
        reg.setSuperficie(region.getSuperficie());
        reg.setCode(region.getCode());

        return new ResponseEntity<>(regionRepo.save(reg), HttpStatus.OK);
    }

    @DeleteMapping("/dropRegion")
    public String deleteRegion(@PathVariable("id") String id){
        regionRepo.deleteById(id);
        return "Deleted with Successfully from database";
    }*/
}
