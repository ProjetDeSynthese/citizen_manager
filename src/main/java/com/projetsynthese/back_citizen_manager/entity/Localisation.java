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

@Data
@Document(collection = "localisation")
@AllArgsConstructor
@NoArgsConstructor
public class Localisation implements Serializable {

    @Id
    private Long id;

    private String name;
    private String code;
    private int superficie;
    private Location location;

    @DocumentReference
    private List<Habitat> habitats = new ArrayList<>();
}
