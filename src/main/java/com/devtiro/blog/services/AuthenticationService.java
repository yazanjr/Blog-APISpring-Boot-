package com.devtiro.blog.services;

import com.devtiro.blog.domain.dtod.AuthResponse;
import com.devtiro.blog.domain.dtod.SignupRequest;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthenticationService {
    UserDetails authenticate(String email, String password);
    String generateToken(UserDetails userDetails);
    UserDetails validateToken(String token);
    void signup(SignupRequest request);
}