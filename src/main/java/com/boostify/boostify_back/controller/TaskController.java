package com.boostify.boostify_back.controller;

import com.boostify.boostify_back.controller.dto.TaskDTO;
import com.boostify.boostify_back.controller.dto.TaskStatusDTO;
import com.boostify.boostify_back.service.task.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long id) {

        return ResponseEntity.status(HttpStatus.OK).body(taskService.findById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TaskDTO>> getAllTaskByUserId(@PathVariable Long userId) {

        return ResponseEntity.status(HttpStatus.OK).body(taskService.findAllByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<TaskDTO> postTask(@RequestBody @Valid TaskDTO taskDTO) {

        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.create(taskDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long id, @RequestBody @Valid TaskDTO taskDTO) {

        return ResponseEntity.status(HttpStatus.OK).body(taskService.update(id, taskDTO));
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<TaskDTO> updateStatusTask(@PathVariable Long id, @RequestBody @Valid TaskStatusDTO newStatus) {

        return ResponseEntity.status(HttpStatus.OK).body(taskService.updateStatus(id, newStatus));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity deleteTask(@PathVariable Long id) {

        taskService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
