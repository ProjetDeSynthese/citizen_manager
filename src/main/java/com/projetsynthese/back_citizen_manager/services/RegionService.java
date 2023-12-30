package com.projetsynthese.back_citizen_manager.services;

import com.projetsynthese.back_citizen_manager.entity.Region;

import java.util.List;

public interface RegionService {

    public void create(Region region);
    public List<Region> findAll();

    public Region findByCode(String code);
    public void deleteByCode(String code);
}
