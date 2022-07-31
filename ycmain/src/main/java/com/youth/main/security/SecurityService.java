package com.youth.main.security;

public interface SecurityService {
	boolean isAuthenticated();
    void autoLogin(String email, String password);
}
