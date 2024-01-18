package com.projetsynthese.back_citizen_manager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "proprietaire")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Proprietaire {

    @Id
    public String id;
    public String nom;
    public String prenom;
    public String email;
    public String contact;
}
