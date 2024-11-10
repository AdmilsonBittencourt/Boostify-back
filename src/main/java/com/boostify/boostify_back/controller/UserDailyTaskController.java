package com.boostify.boostify_back.controller;

import com.boostify.boostify_back.controller.dto.UserDailyTaskDTO;
import com.boostify.boostify_back.service.user_daily_task.UserDailyTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-daily-tasks")
public class UserDailyTaskController {

    private final UserDailyTaskService userDailyTaskService;

    @Autowired
    public UserDailyTaskController(UserDailyTaskService userDailyTaskService) {
        this.userDailyTaskService = userDailyTaskService;
    }

    @PostMapping
    public ResponseEntity<UserDailyTaskDTO> postUserDailyTask(@RequestBody  UserDailyTaskDTO userDailyTaskDTO) {

        return ResponseEntity.status(HttpStatus.OK).body(userDailyTaskService.create(userDailyTaskDTO));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<UserDailyTaskDTO>> getAllUserDailyTasksByUserId(@PathVariable Long id) {

        return ResponseEntity.status(HttpStatus.OK).body(userDailyTaskService.findAllByUserId(id));
    }
}
