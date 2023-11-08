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

@Document(collection = "departement")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Departement implements Serializable {

    @Id
    private Long id;

    private String name;
    private String code;
    private int superficie;

    @DocumentReference
    private List<Ville> villes = new ArrayList<>();
}
