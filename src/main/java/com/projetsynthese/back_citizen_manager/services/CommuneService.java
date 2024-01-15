package com.projetsynthese.back_citizen_manager.services;

import com.projetsynthese.back_citizen_manager.entity.Commune;

import java.util.List;

public interface CommuneService {

    public void create(Commune commune);
    public List<Commune> findAll();

    public void deleteById(String id);
    public Commune findById(String id);

}
