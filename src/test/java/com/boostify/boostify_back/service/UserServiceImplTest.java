package com.boostify.boostify_back.service;

import static com.boostify.boostify_back.common.UserConstants.USER;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import com.boostify.boostify_back.controller.dto.UserDTO;
import com.boostify.boostify_back.exceptions.BadRequestException;
import com.boostify.boostify_back.exceptions.NotFoundException;
import com.boostify.boostify_back.model.User;
import com.boostify.boostify_back.repository.UserRepository;
import com.boostify.boostify_back.service.user.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    void create_WithValidData_ShouldReturnCreatedUserDTO() {
        UserDTO userDTO = new UserDTO(null, "John Doe", "john.doe@example.com", "password123");
        User savedUser = new User(1L, "John Doe", "john.doe@example.com", "hashedPassword");

        when(userRepository.findByEmail(userDTO.getEmail())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(userDTO.getHashedPassword())).thenReturn("hashedPassword");
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        UserDTO result = userService.create(userDTO);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getEmail()).isEqualTo(userDTO.getEmail());

        verify(userRepository, times(1)).findByEmail(userDTO.getEmail());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void create_WithExistingEmail_ShouldThrowBadRequestException() {
        UserDTO userDTO = new UserDTO(null, "John Doe", "john.doe@example.com", "password123");
        User existingUser = new User(1L, "John Doe", "john.doe@example.com", "hashedPassword");

        when(userRepository.findByEmail(userDTO.getEmail())).thenReturn(Optional.of(existingUser));

        assertThatThrownBy(() -> userService.create(userDTO))
                .isInstanceOf(BadRequestException.class)
                .hasMessage("email already exists");

        verify(userRepository, times(1)).findByEmail(userDTO.getEmail());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void findUserById_WithExistingId_ReturnsUserDTO() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(USER));

        UserDTO sut = userService.findById(1L);

        assertThat(sut).isNotNull();
        assertThat(sut.getId()).isEqualTo(USER.getId());
        assertThat(sut.getEmail()).isEqualTo(USER.getEmail());
        assertThat(sut).isInstanceOf(UserDTO.class);
    }

    @Test
    void findById_WithNonExistingId_ShouldThrowNotFoundException() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.findById(1L))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("User not found with ID: 1");

        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void update_WithValidData_ShouldReturnUpdatedUserDTO() {
        User existingUser = new User(1L, "John Doe", "john.doe@example.com", "hashedPassword");
        UserDTO userDTO = new UserDTO(1L, "John Updated", "john.updated@example.com", "password123");
        User updatedUser = new User(1L, "John Updated", "john.updated@example.com", "hashedPassword");

        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        when(userRepository.findByEmail(userDTO.getEmail())).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(updatedUser);

        UserDTO result = userService.update(1L, userDTO);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getEmail()).isEqualTo(userDTO.getEmail());

        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).save(existingUser);
    }

    @Test
    void delete_WithExistingId_ShouldCallRepositoryDelete() {
        User existingUser = new User(1L, "John Doe", "john.doe@example.com", "hashedPassword");

        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));

        userService.delete(1L);

        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void delete_WithNonExistingId_ShouldThrowNotFoundException() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.delete(1L))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("user not found");

        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, never()).deleteById(1L);
    }

}
