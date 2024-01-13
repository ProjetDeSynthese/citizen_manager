package com.projetsynthese.back_citizen_manager.DTO;

import com.projetsynthese.back_citizen_manager.entity.ERole;
import com.projetsynthese.back_citizen_manager.entity.Status;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Data
@NoArgsConstructor
public class UserDTO {
    public String id;

    public String nom_prenom;
    public String email;
    public String username;
    public String password;
    public ERole role;
    public Status status ;
    public String roleTem;
}


