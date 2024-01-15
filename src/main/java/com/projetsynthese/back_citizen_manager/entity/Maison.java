package com.projetsynthese.back_citizen_manager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Document(collection = "maison")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Maison implements Serializable {

    @Id
    public String id;

    public String description;
    public Proprietaire proprietaire;

    public List<String> images;
    @DBRef
    public Quartier quartier;
    @DBRef
    public TypeMaison typeMaison;

}
