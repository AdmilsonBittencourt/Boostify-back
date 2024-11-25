package com.boostify.boostify_back.service;

import com.boostify.boostify_back.controller.dto.TaskDTO;
import com.boostify.boostify_back.enums.Priority;
import com.boostify.boostify_back.enums.Status;
import com.boostify.boostify_back.exceptions.BadRequestException;
import com.boostify.boostify_back.exceptions.NotFoundException;
import com.boostify.boostify_back.model.Task;
import com.boostify.boostify_back.model.User;
import com.boostify.boostify_back.repository.TaskRepository;
import com.boostify.boostify_back.service.task.TaskServiceImpl;
import com.boostify.boostify_back.service.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private TaskServiceImpl taskService;

    @BeforeEach
    void setUp() {
        taskRepository = mock(TaskRepository.class);
        userService = mock(UserService.class);
        taskService = new TaskServiceImpl(taskRepository, userService);
    }

    @Test
    void create_shouldCreateTask() {
        // Given
        Long userId = 1L;
        User user = new User();
        user.setId(userId);

        TaskDTO taskDTO = new TaskDTO(null, userId, "Task Title", "Description", null, null, Priority.LOW);

        when(userService.checkUserExists(userId)).thenReturn(user);
        when(taskRepository.findByTitleAndUser("Task Title", user)).thenReturn(Optional.empty());

        Task savedTask = new Task(user, "Task Title", "Description", Status.PENDING, LocalDate.now(), Priority.LOW);
        savedTask.setId(1L);

        when(taskRepository.save(Mockito.any(Task.class))).thenReturn(savedTask);

        // When
        TaskDTO result = taskService.create(taskDTO);

        // Then
        assertNotNull(result);
        assertEquals("Task Title", result.getTitle());
        assertEquals(userId, result.getIdUser());
        verify(taskRepository).save(any(Task.class));
    }

    @Test
    void create_shouldThrowBadRequestExceptionWhenTitleExists() {
        // Given
        Long userId = 1L;
        User user = new User();
        user.setId(userId);

        TaskDTO taskDTO = new TaskDTO(null, userId, "Existing Title", "Description", null, null, Priority.AVERAGE);

        when(userService.checkUserExists(userId)).thenReturn(user);
        when(taskRepository.findByTitleAndUser("Existing Title", user)).thenReturn(Optional.of(new Task()));

        // When & Then
        assertThrows(BadRequestException.class, () -> taskService.create(taskDTO));
        verify(taskRepository, never()).save(any(Task.class));
    }

    @Test
    void findById_shouldReturnTaskDTO() {
        // Given
        Long taskId = 1L;
        Task task = new Task(new User(), "Task Title", "Description", Status.PENDING, LocalDate.now(), Priority.HIGH);
        task.setId(taskId);

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));

        // When
        TaskDTO result = taskService.findById(taskId);

        // Then
        assertNotNull(result);
        assertEquals(taskId, result.getId());
        assertEquals("Task Title", result.getTitle());
    }

    @Test
    void findById_shouldThrowNotFoundException() {
        // Given
        Long taskId = 1L;

        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(NotFoundException.class, () -> taskService.findById(taskId));
    }

    @Test
    void update_shouldUpdateTask() {
        // Given
        Long taskId = 1L;
        Task existingTask = new Task(new User(), "Old Title", "Old Description", Status.PENDING, LocalDate.now(), Priority.LOW);
        existingTask.setId(taskId);

        TaskDTO taskDTO = new TaskDTO(null, null, "Updated Title", "Updated Description", null, null, Priority.AVERAGE);

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(existingTask));
        when(taskRepository.findByTitleAndUser("Updated Title", existingTask.getUser())).thenReturn(Optional.of(existingTask));

        Task updatedTask = new Task(existingTask.getUser(), "Updated Title", "Updated Description", Status.PENDING, LocalDate.now(), Priority.LOW);
        updatedTask.setId(taskId);

        when(taskRepository.save(existingTask)).thenReturn(updatedTask);

        // When
        TaskDTO result = taskService.update(taskId, taskDTO);

        // Then
        assertNotNull(result);
        assertEquals("Updated Title", result.getTitle());
        assertEquals("Updated Description", result.getDescription());
        verify(taskRepository).save(existingTask);
    }

    @Test
    void delete_shouldDeleteTask() {
        // Given
        Long taskId = 1L;
        Task existingTask = new Task();
        existingTask.setId(taskId);

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(existingTask));

        // When
        taskService.delete(taskId);

        // Then
        verify(taskRepository).deleteById(taskId);
    }

    @Test
    void delete_shouldThrowNotFoundException() {
        // Given
        Long taskId = 1L;

        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(NotFoundException.class, () -> taskService.delete(taskId));
        verify(taskRepository, never()).deleteById(taskId);
    }
}
