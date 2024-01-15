package com.projetsynthese.back_citizen_manager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "maison")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeMaison {
    @Id
    public String id;
    public String name;
}
