package com.projetsynthese.back_citizen_manager.services;

import com.projetsynthese.back_citizen_manager.entity.User;

import java.util.List;

public interface UserService   {

    public void create(User user);
    public List<User> findAll();
}
