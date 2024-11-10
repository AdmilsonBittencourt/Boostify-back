package com.boostify.boostify_back.controller;

import com.boostify.boostify_back.controller.dto.LoginRequestDTO;
import com.boostify.boostify_back.controller.dto.LoginResponseDTO;
import com.boostify.boostify_back.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO login){

        return ResponseEntity.status(HttpStatus.OK).body(authService.checkLogin(login));
    }
}
