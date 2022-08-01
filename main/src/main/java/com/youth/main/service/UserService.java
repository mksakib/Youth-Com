package com.youth.main.service;

import org.springframework.security.core
                     .userdetails.UserDetailsService;
import com.youth.main.model.UserModel;
import com.youth.main.web.dto.UserRegistrationDto;


public interface UserService 
             extends UserDetailsService {
   
   UserModel save(UserRegistrationDto registrationDto);
//   List<User> getAll();
   
   UserModel findByUsername(String username);
}
