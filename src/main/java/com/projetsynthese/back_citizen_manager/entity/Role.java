package com.projetsynthese.back_citizen_manager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "secteur")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    public String id;
   // @Enumerated(EnumType.STRING)
    public String label;

}