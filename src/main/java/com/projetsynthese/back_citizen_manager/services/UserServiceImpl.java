package com.projetsynthese.back_citizen_manager.services;

import com.projetsynthese.back_citizen_manager.entity.User;
import com.projetsynthese.back_citizen_manager.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl  implements  UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;


    public void create(User user){
        if (!user.getEmail().contains("@") )  {
           throw  new RuntimeException("Email not match");
        }
        if ( !user.getEmail().contains(".") ) {
           throw  new RuntimeException("Email non match");
        }
        Optional<Object> userOptional = this.userRepository.findByEmail(user.getEmail());
        if(userOptional.isPresent()){
            throw  new RuntimeException("Your email is already used");
        }
       user.setPassword(this.passwordEncoder.encode(user.getPassword()));
      //  Role userRole = new Role();
        //userRole.setLabel(RoleEnum.ADMINISTRATOR);
        //user.setRole(userRole);
       // this.userRepository.save(user);

    }


}
