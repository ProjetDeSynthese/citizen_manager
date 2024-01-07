package com.projetsynthese.back_citizen_manager.DTO;

import com.projetsynthese.back_citizen_manager.entity.Secteur;
import com.projetsynthese.back_citizen_manager.entity.TypeHabitat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

@Data
@NoArgsConstructor
public class HabitatDTO {
    public String id;
    public String description;
    public String proprietaire;
    public String adresse;
    public Double latitude;
    public Double longitude;
    public List<String> images;
    public Secteur secteur;
    public TypeHabitat typeHabitat;

}
