package com.projetsynthese.back_citizen_manager.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
@NoArgsConstructor
public class RegionDTO {


    public String id;

    public String name;
    public String code;
}
