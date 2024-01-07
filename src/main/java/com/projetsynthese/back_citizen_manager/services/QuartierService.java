package com.projetsynthese.back_citizen_manager.services;

import com.projetsynthese.back_citizen_manager.entity.Quartier;

import java.util.List;

public interface QuartierService {
    public void create(Quartier quartier);
    public List<Quartier> findAll();
    public List<Quartier> findByCommune(String commune);

    public Quartier findByCode(String code);
    public void deleteById(String id);
    public Quartier findById(String id);
}
