package com.projetsynthese.back_citizen_manager.DTO;

import com.projetsynthese.back_citizen_manager.entity.Habitat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class CitoyenDTO {
    public String id;
    public String name;
    public String prenom;
    public String sexe;
    public String email;
    public String situationMat;
    public String phone;
    public String nationalite;
    public String profession;
    public String numCni;
    public List<Habitat> habitats = new ArrayList<>();
}
