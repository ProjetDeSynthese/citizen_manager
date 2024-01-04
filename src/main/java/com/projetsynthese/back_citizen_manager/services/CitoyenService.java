package com.projetsynthese.back_citizen_manager.services;

import com.projetsynthese.back_citizen_manager.entity.Citoyen;

import java.util.List;

public interface CitoyenService {

    public void create(Citoyen citoyen);
    public List<Citoyen> findAll();

    public Citoyen findByCni(String code);
    public void deleteById(String id);
    public Citoyen findById(String id);
}
