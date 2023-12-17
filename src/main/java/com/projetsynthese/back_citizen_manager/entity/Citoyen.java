package com.projetsynthese.back_citizen_manager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "citoyen")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Citoyen implements Serializable {

    @Id
    private String id;

    @NonNull
    private String name;
    private String prenom;
    private String sexe;
    @Indexed(unique = true)
    private String email;
    private String situationMat;
    private String phone;
    private String nationalite;
    private String profession;
    @NonNull
    @Indexed(unique = true)
    private String numCni;

    @DBRef
    private List<Habitat> habitats = new ArrayList<>();
}
