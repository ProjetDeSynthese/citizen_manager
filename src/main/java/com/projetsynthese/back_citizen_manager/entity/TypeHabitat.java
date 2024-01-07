package com.projetsynthese.back_citizen_manager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "typeHabitat")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeHabitat {
    @Id
    public String id;
    public String name;
}
