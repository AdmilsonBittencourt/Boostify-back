package com.boostify.boostify_back.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {

    private Long id;

    @NotBlank
    private String name;

    @Email(message = "Email não valido")
    private String email;

    @Size(min = 8)
    private String hashedPassword;

    public UserDTO(String name, String email, String hashedPassword) {
        this.name = name;
        this.email = email;
        this.hashedPassword = hashedPassword;
    }

    public UserDTO(String name, String email) {
        this.email = email;
        this.name = name;
    }
}

