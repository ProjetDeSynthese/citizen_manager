package com.projetsynthese.back_citizen_manager.controller;

import com.projetsynthese.back_citizen_manager.entity.Region;
import com.projetsynthese.back_citizen_manager.repository.RegionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RegionController {

    @Autowired
    private RegionRepo regionRepo;

    @PostMapping("/addRegion")
    public ResponseEntity<Region> saveRegion(@RequestBody Region region){

        if (region == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(regionRepo.save(region), HttpStatus.OK);
    }

//    Liste de toutes les regions
    @GetMapping("/findAllRegion")
    public List<Region> getAllRegion(){
        return regionRepo.findAll();
    }

    @PutMapping("/updateRegion/{id}")
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
    }
}
