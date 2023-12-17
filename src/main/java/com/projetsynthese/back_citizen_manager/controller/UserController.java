package com.projetsynthese.back_citizen_manager.controller;

import com.projetsynthese.back_citizen_manager.entity.User;
import com.projetsynthese.back_citizen_manager.repository.UserRepo;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepo userRepo;

    //Ajouter un utilisateur
    @PostMapping("/addUser")
    public ResponseEntity<User> saveUser(@RequestBody User user){

        if (user == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        User saveUser = userRepo.save(user);
        return new ResponseEntity<>(saveUser, HttpStatus.OK);
    }

    //Liste des utilisateurs
    @GetMapping("/findAllUser")
    public List<User> getAllUser(){
        return userRepo.findAll();
    }

    //Rechercher un utilisateur par id
    @GetMapping("/findUserById/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id){

        User userFromDB = userRepo.findById(id).orElse(null);
        if (userFromDB == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userFromDB, HttpStatus.OK);
    }

    //Mise a jour du status de l'utilisateur
    @PatchMapping("/updateUserStatus/{id}/active")
    public ResponseEntity<User> editUserStatus(@PathVariable String id, @RequestParam boolean status){

        User user = userRepo.findById(id).orElse(null);
        if (user == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        user.setActive(status);
        User updateUser = userRepo.save(user);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    //Modifier un utilisateur
    @PutMapping("/updateUser/{id}")
    public ResponseEntity<User> editUser(@PathVariable String id, @RequestBody User user){

        User userFromDB = userRepo.findById(id).orElse(null);
        if (userFromDB == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        userFromDB.setName(user.getName());
        userFromDB.setEmail(user.getEmail());
        userFromDB.setActive(user.getActive());

        User updateUser = userRepo.save(userFromDB);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    //Supprimer un utilisateur
    @DeleteMapping("/dropUser/{id}")
    public String deleteUser(@PathVariable String id){
        userRepo.deleteById(id);
        return "Deleted with Successfully from database";
    }
}
