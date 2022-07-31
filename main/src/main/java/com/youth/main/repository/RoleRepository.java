package com.youth.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.youth.main.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	   
//	   @Query("SELECT u.email, u.password FROM user WHERE u.email = ?")
	   Role findByName(String name);
	   
	   
}
