package com.youth.main.service;

import org.springframework.security.core
                     .userdetails.UserDetailsService;

import com.youth.main.model.User;
import com.youth.main.web.dto.UserRegistrationDto;

public interface UserService 
             extends UserDetailsService {
   
   User save(UserRegistrationDto registrationDto);
//   List<User> getAll();
   
   User findByUsername(String username);
}
