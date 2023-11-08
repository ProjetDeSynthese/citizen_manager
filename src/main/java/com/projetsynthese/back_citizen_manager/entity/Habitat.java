package com.projetsynthese.back_citizen_manager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "habitat")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Habitat implements Serializable {

    @Id
    private Long id;

    private String adresse;
    private int superficie;
    private int nombreChambre;
    private String proprietaire;
    private int anneeConstruct;
    private int etage;
    private String parking;

//    habitat-citoyen
    @DocumentReference
    private List<Citoyen> citoyens = new ArrayList<>();
}
