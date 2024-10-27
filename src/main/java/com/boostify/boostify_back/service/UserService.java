package com.boostify.boostify_back.service;

import com.boostify.boostify_back.model.User;
import com.boostify.boostify_back.model.dto.UserDTO;
import com.boostify.boostify_back.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDTO createUser(UserDTO userDTO) {

        Optional<User> byEmail = userRepository.findByEmail(userDTO.getEmail());

        if(byEmail.isPresent()) {
            throw new RuntimeException("Email já cadastrado");
        }

        String HashedPassword = passwordEncoder.encode(userDTO.getHashedPassword());

        User save = userRepository.save(new User(userDTO.getName(), userDTO.getEmail(),HashedPassword));

        return new UserDTO(save.getId(), save.getName(), save.getEmail(), save.getHashedPassword());
    }

    public UserDTO getUserById(int id) {

        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found"));

        return new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getHashedPassword());
    }

    public UserDTO updateUser(int id, UserDTO userDTO) {

        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found"));

        Optional<User> byEmail = userRepository.findByEmail(userDTO.getEmail());
        if(byEmail.isPresent() && !byEmail.get().getEmail().equals(user.getEmail())) throw new RuntimeException("email já cadastrado");

        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        User save = userRepository.save(user);

        return new UserDTO(save.getId(), save.getName(), save.getEmail(), save.getHashedPassword());
    }

    public void deleteUser(int id) {

        userRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found"));
        userRepository.deleteById(id);
    }
}
