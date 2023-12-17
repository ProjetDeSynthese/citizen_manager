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
    private String id;

    private String adresse;
    private float superficie;
    private int nombreChambre;
    private String proprietaire;
    private int anneeConstruct;
    private int etage;
    private int parking;
    private boolean libre = false;

    @DBRef
    private Secteur secteur;

}
