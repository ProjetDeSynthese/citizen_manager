package com.projetsynthese.back_citizen_manager.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProprietaireDTO {
    public String id;
    public String nom;
    public String prenom;
    public String email;
    public String contact;
}
