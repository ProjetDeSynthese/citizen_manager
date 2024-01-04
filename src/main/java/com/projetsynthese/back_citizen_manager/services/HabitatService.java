package com.projetsynthese.back_citizen_manager.services;

import com.projetsynthese.back_citizen_manager.entity.Habitat;

import java.util.List;

public interface HabitatService {

    public void create(Habitat habitat);
    public List<Habitat> findAll();

    public Habitat findByAddress(String address);
    public void deleteById(String code);
    public Habitat findById(String id);
}
