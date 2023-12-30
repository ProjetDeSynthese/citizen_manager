package com.projetsynthese.back_citizen_manager.DTO;

import com.projetsynthese.back_citizen_manager.entity.Region;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DepartementDTO {

    public String id;

    public String name;
    public String code;
    public Region region;
}
