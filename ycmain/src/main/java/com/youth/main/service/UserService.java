package com.youth.main.service;

import com.youth.main.model.User;

public interface UserService {
	void save(User user);

    User findByUsername(String email);
}