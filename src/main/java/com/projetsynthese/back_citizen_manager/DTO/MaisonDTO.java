package com.projetsynthese.back_citizen_manager.DTO;

import com.projetsynthese.back_citizen_manager.entity.TypeMaison;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class MaisonDTO {
    public String id;
    public String description;
    public String proprietaire;
    //public String adresse;
    public Double latitude;
    public Double longitude;
    public Double prix;
    public List<String> images;
    public QuartierDTO quartierDTO;
    public TypeMaison typeMaison;

}
