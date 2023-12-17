package com.projetsynthese.back_citizen_manager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "departement")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Departement implements Serializable {

    @Id
    private String id;

    @NonNull
    @Indexed(unique = true)
    private String name;
    private String code;
    private float superficie;

    @DBRef
    private Region region;
}
