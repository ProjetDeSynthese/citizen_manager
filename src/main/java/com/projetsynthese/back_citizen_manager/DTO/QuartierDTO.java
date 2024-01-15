package com.projetsynthese.back_citizen_manager.DTO;

import com.projetsynthese.back_citizen_manager.entity.Commune;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
@Data
@NoArgsConstructor
public class QuartierDTO {
    public String id;

    public String name;
    public Commune commune;
}