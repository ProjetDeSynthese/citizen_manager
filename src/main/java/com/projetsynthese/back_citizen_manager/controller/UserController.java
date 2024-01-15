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

}
