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
    public String id;

    @NonNull
    public String name;
    public String prenom;
    public String sexe;
    @Indexed(unique = true)
    public String email;
    public String situationMat;
    public String phone;
    public String nationalite;
    public String profession;
    @NonNull
    @Indexed(unique = true)
    public String numCni;

    @DBRef
    public List<Habitat> habitats = new ArrayList<>();
}
