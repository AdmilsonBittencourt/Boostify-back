package com.boostify.boostify_back.service;

import com.boostify.boostify_back.controller.dto.LoginRequestDTO;
import com.boostify.boostify_back.controller.dto.LoginResponseDTO;
import com.boostify.boostify_back.exceptions.InvalidCredentialsException;
import com.boostify.boostify_back.infra.security.TokenService;
import com.boostify.boostify_back.model.User;
import com.boostify.boostify_back.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @Autowired
    public AuthService(UserService userService, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    public LoginResponseDTO checkLogin(LoginRequestDTO loginRequestDTO) {

        User user = userService.findByEmail(loginRequestDTO.email());

        if(!passwordEncoder.matches(loginRequestDTO.password(), user.getHashedPassword()))
            throw new InvalidCredentialsException("Invalid email or password");

        String token = tokenService.generateToken(user);
        return new LoginResponseDTO(token, user.getId());
    }
}
