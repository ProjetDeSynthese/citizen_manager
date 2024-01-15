package com.projetsynthese.back_citizen_manager.services;

import com.projetsynthese.back_citizen_manager.entity.Proprietaire;

import java.util.List;

public interface ProprietaireService {

    public void create(Proprietaire proprietaire);
    public List<Proprietaire> findAll();

    public void deleteById(String code);
    public Proprietaire findById(String id);
}
