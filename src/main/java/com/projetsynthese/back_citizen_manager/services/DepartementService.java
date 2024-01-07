package com.projetsynthese.back_citizen_manager.services;

import com.projetsynthese.back_citizen_manager.entity.Departement;

import java.util.List;

public interface DepartementService {
    public void create(Departement departement);
    public List<Departement> findAll();

    public Departement findByCode(String code);
    public void deleteById(String id);
    public Departement findById(String id);


    public List<Departement> findByRegion(String region_id);
}
