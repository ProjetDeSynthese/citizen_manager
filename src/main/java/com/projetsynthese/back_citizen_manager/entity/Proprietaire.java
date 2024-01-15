package com.projetsynthese.back_citizen_manager.entity;

import org.springframework.data.annotation.Id;

public class Proprietaire {

    @Id
    public String id;
    public String nom;
    public String prenom;
    public String email;
    public String contact;
}
