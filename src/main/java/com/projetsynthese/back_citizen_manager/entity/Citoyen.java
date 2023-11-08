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

@Document(collection = "citoyen")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Citoyen implements Serializable {

    @Id
    private Long id;

    private String name;
    private String prenom;
    private String sexe;
    private String email;
    private String situationMat;
    private String phone;
    private String nationalite;
    private String profession;
    private String numCni;

    @DocumentReference
    private List<Habitat> habitats = new ArrayList<>();
}
