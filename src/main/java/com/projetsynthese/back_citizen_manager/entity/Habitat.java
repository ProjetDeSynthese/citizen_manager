package com.projetsynthese.back_citizen_manager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "habitat")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Habitat implements Serializable {

    @Id
    public String id;

    public String adresse;
    public float superficie;
    public int nombreChambre;
    public String proprietaire;
    public int anneeConstruct;
    public int etage;
    public int parking;
    public boolean libre = false;

    @DBRef
    public Secteur secteur;

}
