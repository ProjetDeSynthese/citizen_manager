package com.projetsynthese.back_citizen_manager.services;


import com.projetsynthese.back_citizen_manager.entity.Ville;

import java.util.List;

public interface VilleService {
    public void create(Ville ville);
    public List<Ville> findAll();

    public Ville findByCode(String code);
    public void deleteById(String code);
    public Ville findById(String id);
}
