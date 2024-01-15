package com.projetsynthese.back_citizen_manager.services;

import com.projetsynthese.back_citizen_manager.entity.Maison;

import java.util.List;

public interface MaisonService {

    public void create(Maison maison);
    public List<Maison> findAll();

    public void deleteById(String code);
    public Maison findById(String id);
}
