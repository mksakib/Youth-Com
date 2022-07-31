package com.youth.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.youth.main.model.User;

@Repository
public interface UserRepository 
       extends JpaRepository<User, Long> {
   
//   @Query("SELECT u FROM User u WHERE u.email = ?1")
   User findByEmail(String email);
}
