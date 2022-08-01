package com.youth.main.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.youth.main.model.Role;
import com.youth.main.model.UserModel;
import com.youth.main.repository.RoleRepository;
import com.youth.main.repository.UserRepository;
import com.youth.main.web.dto.UserRegistrationDto;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserModel userModelLoad = userRepository.findByEmail(username);
		if(userModelLoad == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		
		return new org.springframework.security.core.userdetails.User(userModelLoad.getEmail(), userModelLoad.getPassword(), mapRolesToAuthorities(userModelLoad.getRoles()));		
		
	}	

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
		return roles.stream()
				.map(role -> new SimpleGrantedAuthority(role.getName()))
				.collect(Collectors.toList());
	
	}

	@Override
	public UserModel save(UserRegistrationDto registrationDto) {
		UserModel userModel = new UserModel(
		registrationDto.getFirstName(),
		registrationDto.getLastName(),
		registrationDto.getEmail(),
		registrationDto.getPassword(),
		Arrays.asList(roleRepository.findByName("ROLE_USER")));
		
		return userRepository.save(userModel);
	}

	@Override
	public UserModel findByUsername(String email) {
		return userRepository.findByEmail(email);
	}

//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		return null;
//	}

}



















