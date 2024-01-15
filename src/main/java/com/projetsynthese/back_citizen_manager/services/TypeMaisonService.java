package com.projetsynthese.back_citizen_manager.services;

import com.projetsynthese.back_citizen_manager.entity.TypeMaison;

import java.util.List;

public interface TypeMaisonService {
    public void create(TypeMaison typeHabitat);
    public List<TypeMaison> findAll();
    public void deleteById(String id);
    public TypeMaison findById(String id);
}
