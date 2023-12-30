package com.projetsynthese.back_citizen_manager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "region")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Region implements Serializable {

    @Id
    public String id;

    @NonNull
    @Indexed(unique = true)
    public String name;
    public String code;

}
