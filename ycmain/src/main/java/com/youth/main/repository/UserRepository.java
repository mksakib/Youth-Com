package com.youth.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.youth.main.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
