package com.projetsynthese.back_citizen_manager.controller;

import com.projetsynthese.back_citizen_manager.DTO.UserDTO;
import com.projetsynthese.back_citizen_manager.entity.User;
import com.projetsynthese.back_citizen_manager.exeption.message.Message;
import com.projetsynthese.back_citizen_manager.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user/")
@CrossOrigin(origins = "*")
public class UserController {


    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<Message> save(@RequestBody User user){
        if (user == null){
            Message message = Message.builder()
                    .code(500).message("Entity is required")
                    .build();
            return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
        }
        this.userService.create(user);
        Message message = Message.builder()
                .code(201).message("Record Successfully")
                .build();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @GetMapping()
    public ResponseEntity<List<UserDTO>> findAll(){
        return new ResponseEntity<>( this.userService.findAll()
                .stream()
                .map(user -> modelMapper.map(user,UserDTO.class))
                .collect(Collectors.toList()),HttpStatus.OK );

    }
    /*@GetMapping("{id}")
    public ResponseEntity<TypeHabitatDTO> findById(@PathVariable String id){
        return new ResponseEntity<>(
                modelMapper.map(this.typeHabitatService.findById(id),TypeHabitatDTO.class),
                HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Message>  deleteById(@PathVariable String id){
        try{
            this.typeHabitatService.deleteById(id);
            Message message = Message.builder().message("Successfully").code(201).build();
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        catch (Exception e){
            Message message = Message.builder().message("Error").code(500).build();
            return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);

        }

    }*/
/*
    @Autowired
    private UserRepository userRepo;

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
    }*/
}
