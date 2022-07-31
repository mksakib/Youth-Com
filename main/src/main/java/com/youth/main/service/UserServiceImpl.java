package com.youth.main.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.youth.main.model.User;
import com.youth.main.repository.RoleRepository;
import com.youth.main.repository.UserRepository;
import com.youth.main.web.dto.UserRegistrationDto;

public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return null;
	}

	@Override
	public User save(UserRegistrationDto registrationDto) {
		User user = new User();
		user.setFirstName(registrationDto.getFirstName());
		user.setLastName(registrationDto.getLastName());
		user.setEmail(registrationDto.getEmail());
		user.setPassword(registrationDto.getPassword());
		user.setRoles(Arrays.asList(roleRepository.findByName("USER")));
		
		return userRepository.save(user);
	}

	@Override
	public User findByUsername(String email) {
		return userRepository.findByEmail(email);
	}

}



















