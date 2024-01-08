package com.projetsynthese.back_citizen_manager.controller.auth;

import com.projetsynthese.back_citizen_manager.DTO.AuthentificationDTO;
import com.projetsynthese.back_citizen_manager.repository.UserRepository;
import com.projetsynthese.back_citizen_manager.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final JwtService jwtService;
    private final UserRepository userRepository;
    public  final AuthenticationManager authenticationManager;

    public  AuthenticationResponse authenticate(AuthentificationDTO authentificationDTO){
        final Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authentificationDTO.getUsername(), authentificationDTO.getPassword())
        );

        if(authenticate.isAuthenticated()) {
            var user = this.userRepository.findByEmail(authentificationDTO.getUsername())
                    .orElseThrow();
            var jwtToken = jwtService.generateToken((UserDetails) user);
            return AuthenticationResponse.builder()
                    .accessToken(jwtToken)
                    .role(String.valueOf(((UserDetails) user).getAuthorities()))
                    .build();
        }
        return null;

    }
}
