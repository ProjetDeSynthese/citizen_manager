package com.projetsynthese.back_citizen_manager.repository;

import com.projetsynthese.back_citizen_manager.entity.Region;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RegionRepository extends MongoRepository<Region, String> {

    public Optional<Region> findByName(String name);
    public Optional<Region> findByCode(String code);

    //void delete(Optional<Region> regionOptional);
}
