package com.projetsynthese.back_citizen_manager.services;

import com.projetsynthese.back_citizen_manager.entity.Departement;
import com.projetsynthese.back_citizen_manager.entity.Secteur;

import java.util.List;

public interface SecteurService {

    public void create(Secteur secteur);
    public List<Secteur> findAll();

    public Secteur findByCode(String code);
    public void deleteById(String code);
    public Secteur findById(String id);
}
