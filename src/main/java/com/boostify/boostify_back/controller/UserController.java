package com.boostify.boostify_back.controller;

import com.boostify.boostify_back.controller.dto.UserDTO;
import com.boostify.boostify_back.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {

        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
    }

    @PostMapping()
    public ResponseEntity<UserDTO> postUser(@RequestBody @Valid UserDTO userDTO) {

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(userDTO));

    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> putUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {

        return ResponseEntity.status(HttpStatus.OK).body(userService.update(id, userDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable Long id) {

        userService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
