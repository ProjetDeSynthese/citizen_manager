package com.projetsynthese.back_citizen_manager.DTO;

import com.projetsynthese.back_citizen_manager.entity.Secteur;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HabitatDTO {
    public String id;
    public String adresse;
    public float superficie;
    public int nombreChambre;
    public String proprietaire;
    public int anneeConstruct;
    public int etage;
    public int parking;
    public boolean libre = false;
    public Secteur secteur;
}
