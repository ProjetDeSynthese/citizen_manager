package com.projetsynthese.back_citizen_manager.DTO;

import com.projetsynthese.back_citizen_manager.entity.Proprietaire;
import com.projetsynthese.back_citizen_manager.entity.Quartier;
import com.projetsynthese.back_citizen_manager.entity.TypeMaison;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class MaisonDTO {
    public String id;
    public String description;
    public Proprietaire proprietaire;
    //public String adresse;
    public Double latitude;
    public Double longitude;
    public Double prix;
    public List<String> images;
    public Quartier quartier;
    public TypeMaison typeMaison;

}
