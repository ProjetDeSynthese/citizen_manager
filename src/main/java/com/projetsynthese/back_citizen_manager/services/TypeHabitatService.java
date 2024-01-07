package com.projetsynthese.back_citizen_manager.services;

import com.projetsynthese.back_citizen_manager.entity.TypeHabitat;

import java.util.List;

public interface TypeHabitatService {
    public void create(TypeHabitat typeHabitat);
    public List<TypeHabitat> findAll();
    public void deleteById(String id);
    public TypeHabitat findById(String id);
}
