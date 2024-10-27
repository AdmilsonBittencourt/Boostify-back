package com.boostify.boostify_back.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {

    private Integer id;
    private String name;
    private String email;
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

