package com.projetsynthese.back_citizen_manager.controller.auth;

import com.projetsynthese.back_citizen_manager.DTO.AuthentificationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth/")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    @PostMapping("authenticate")
    public ResponseEntity<Object> connexion(@RequestBody AuthentificationDTO authentificationDTO) {

        return new ResponseEntity<>(this.authenticationService.authenticate(authentificationDTO), HttpStatus.OK);
    }
}
