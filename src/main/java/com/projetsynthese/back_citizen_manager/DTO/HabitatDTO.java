package com.projetsynthese.back_citizen_manager.DTO;

import com.projetsynthese.back_citizen_manager.entity.Secteur;
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
    public Double latitude;
    public Double longitude;
    public List<String> images;
    public Secteur secteur;
}
