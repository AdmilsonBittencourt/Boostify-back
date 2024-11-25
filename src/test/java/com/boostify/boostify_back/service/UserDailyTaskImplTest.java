package com.boostify.boostify_back.service;

import com.boostify.boostify_back.controller.dto.UserDailyTaskDTO;
import com.boostify.boostify_back.exceptions.NotFoundException;
import com.boostify.boostify_back.model.Task;
import com.boostify.boostify_back.model.User;
import com.boostify.boostify_back.model.UserDailyTask;
import com.boostify.boostify_back.repository.UserDailyTaskRepository;
import com.boostify.boostify_back.service.task.TaskService;
import com.boostify.boostify_back.service.user.UserService;
import com.boostify.boostify_back.service.user_daily_task.UserDailyTaskImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserDailyTaskImplTest {

//    @InjectMocks
//    private UserDailyTaskImpl userDailyTaskService;
//
//    @Mock
//    private UserDailyTaskRepository repository;
//
//    @Mock
//    private UserService userService;
//
//    @Mock
//    private TaskService taskService;
//
//    @BeforeEach
//    void setUp() {
//        openMocks(this);
//    }
//
//    @Test
//    void create_shouldCreateUserDailyTask() {
//        // Given
//        Long userId = 1L;
//        Long taskId = 2L;
//
//        User user = new User();
//        user.setId(userId);
//
//        Task task = new Task();
//        task.setId(taskId);
//
//        UserDailyTask userDailyTask = new UserDailyTask(user, task);
//        UserDailyTaskDTO dto = new UserDailyTaskDTO(userId, taskId);
//
//        when(userService.checkUserExists(userId)).thenReturn(user);
//        when(taskService.checkTaskExists(taskId)).thenReturn(task);
//        when(repository.save(any(UserDailyTask.class))).thenReturn(userDailyTask);
//
//        // When
//        UserDailyTaskDTO result = userDailyTaskService.create(dto);
//
//        // Then
//        assertNotNull(result);
//        assertEquals(userId, result.getIdUser());
//        assertEquals(taskId, result.getIdTask());
//        verify(repository).save(any(UserDailyTask.class));
//    }
//
//    @Test
//    void findByUserIdAndTaskId_shouldReturnUserDailyTask() {
//        // Given
//        Long userId = 1L;
//        Long taskId = 2L;
//
//        User user = new User();
//        user.setId(userId);
//
//        Task task = new Task();
//        task.setId(taskId);
//
//        UserDailyTask userDailyTask = new UserDailyTask(user, task);
//
//        when(repository.findByUserIdAndTaskId(userId, taskId)).thenReturn(Optional.of(userDailyTask));
//
//        // When
//        UserDailyTaskDTO result = userDailyTaskService.findByUserIdAndTaskId(userId, taskId);
//
//        // Then
//        assertNotNull(result);
//        assertEquals(userId, result.getIdUser());
//        assertEquals(taskId, result.getIdTask());
//    }
//
//    @Test
//    void findByUserIdAndTaskId_shouldThrowNotFoundException() {
//        // Given
//        Long userId = 1L;
//        Long taskId = 2L;
//
//        when(repository.findByUserIdAndTaskId(userId, taskId)).thenReturn(Optional.empty());
//
//        // When & Then
//        assertThrows(NotFoundException.class, () -> userDailyTaskService.findByUserIdAndTaskId(userId, taskId));
//    }
//
//    @Test
//    void findAllByUserId_shouldReturnListOfUserDailyTasks() {
//        // Given
//        Long userId = 1L;
//
//        User user = new User();
//        user.setId(userId);
//
//        Task task1 = new Task();
//        task1.setId(1L);
//
//        Task task2 = new Task();
//        task2.setId(2L);
//
//        UserDailyTask userDailyTask1 = new UserDailyTask(user, task1);
//        UserDailyTask userDailyTask2 = new UserDailyTask(user, task2);
//
//        when(repository.findAllByUserId(userId)).thenReturn(List.of(userDailyTask1, userDailyTask2));
//
//        // When
//        List<UserDailyTaskDTO> result = userDailyTaskService.findAllByUserId(userId);
//
//        // Then
//        assertNotNull(result);
//        assertEquals(2, result.size());
//    }
//
//    @Test
//    void findAllByTaskId_shouldReturnListOfUserDailyTasks() {
//        // Given
//        Long taskId = 1L;
//
//        User user1 = new User();
//        user1.setId(1L);
//
//        User user2 = new User();
//        user2.setId(2L);
//
//        Task task = new Task();
//        task.setId(taskId);
//
//        UserDailyTask userDailyTask1 = new UserDailyTask(user1, task);
//        UserDailyTask userDailyTask2 = new UserDailyTask(user2, task);
//
//        when(repository.findAllByTaskId(taskId)).thenReturn(List.of(userDailyTask1, userDailyTask2));
//
//        // When
//        List<UserDailyTaskDTO> result = userDailyTaskService.findAllByTaskId(taskId);
//
//        // Then
//        assertNotNull(result);
//        assertEquals(2, result.size());
//    }
//
//    @Test
//    void delete_shouldDeleteUserDailyTask() {
//        // Given
//        Long userId = 1L;
//        Long taskId = 2L;
//
//        User user = new User();
//        user.setId(userId);
//
//        Task task = new Task();
//        task.setId(taskId);
//
//        UserDailyTask userDailyTask = new UserDailyTask(user, task);
//
//        when(repository.findByUserIdAndTaskId(userId, taskId)).thenReturn(Optional.of(userDailyTask));
//
//        // When
//        userDailyTaskService.delete(userId, taskId);
//
//        // Then
//        verify(repository).delete(userDailyTask);
//    }
//
//    @Test
//    void delete_shouldThrowNotFoundExceptionWhenNotFound() {
//        // Given
//        Long userId = 1L;
//        Long taskId = 2L;
//
//        when(repository.findByUserIdAndTaskId(userId, taskId)).thenReturn(Optional.empty());
//
//        // When & Then
//        assertThrows(NotFoundException.class, () -> userDailyTaskService.delete(userId, taskId));
//    }
}
