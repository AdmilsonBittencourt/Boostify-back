package com.boostify.boostify_back.service.user;

import com.boostify.boostify_back.model.User;
import com.boostify.boostify_back.controller.dto.UserDTO;
import com.boostify.boostify_back.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTO create(UserDTO userDTO) {

        Optional<User> byEmail = userRepository.findByEmail(userDTO.getEmail());

        if(byEmail.isPresent()) {
            throw new RuntimeException("Email já cadastrado");
        }

        String HashedPassword = passwordEncoder.encode(userDTO.getHashedPassword());

        User save = userRepository.save(new User(userDTO.getName(), userDTO.getEmail(),HashedPassword));

        return new UserDTO(save.getId(), save.getName(), save.getEmail(), save.getHashedPassword());
    }

    @Override
    public UserDTO findById(Long id) {

        User user = checkUserExists(id);

        return new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getHashedPassword());
    }

    @Override
    public UserDTO update(Long id, UserDTO userDTO) {

        User user = checkUserExists(id);

        Optional<User> byEmail = userRepository.findByEmail(userDTO.getEmail());
        if(byEmail.isPresent() && !byEmail.get().getEmail().equals(user.getEmail())) throw new RuntimeException("email já cadastrado");

        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        User save = userRepository.save(user);

        return new UserDTO(save.getId(), save.getName(), save.getEmail(), save.getHashedPassword());
    }

    @Override
    public void delete(Long id) {

        checkUserExists(id);
        userRepository.deleteById(id);
    }

    @Override
    public User checkUserExists(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found"));
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("user not found"));
    }
}
