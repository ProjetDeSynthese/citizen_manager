package com.projetsynthese.back_citizen_manager.services;

import com.projetsynthese.back_citizen_manager.entity.Citoyen;
import com.projetsynthese.back_citizen_manager.entity.Region;
import com.projetsynthese.back_citizen_manager.exeption.EntityNotFoundException;
import com.projetsynthese.back_citizen_manager.repository.RegionRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RegionServiceImpl implements  RegionService{
    @Autowired
    private RegionRepository regionRepository;

    public void create(Region region) {
      //  Optional<Region> regionOptional = Optional.ofNullable(findByCode(region.code));

        regionRepository.save(region);
    }

    public List<Region> findAll() {
        Optional<List<Region>> optionalRegionList = Optional.of(this.regionRepository.findAll());
        return optionalRegionList.orElseThrow(
                ()-> new EntityNotFoundException("Not Found")
        );
    }

    public Region findByCode(String code) {
        Optional<Region> optionalRegion = regionRepository.findByCode(code);
        return optionalRegion.orElseThrow(()->new EntityNotFoundException());

    }
    public Region findById(String id) {
        Optional<Region> optionalRegion = regionRepository.findById(id);
        return optionalRegion.orElseThrow(()->new EntityNotFoundException());
    }

    public void deleteById(String id){
        regionRepository.deleteById(id);
    }
}
