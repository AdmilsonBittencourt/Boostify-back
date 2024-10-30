package com.boostify.boostify_back;

import com.boostify.boostify_back.enums.Priority;
import com.boostify.boostify_back.enums.Status;
import com.boostify.boostify_back.model.dto.TaskDTO;
import com.boostify.boostify_back.model.dto.UserDTO;
import com.boostify.boostify_back.service.task.TaskServiceImpl;
import com.boostify.boostify_back.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class AppRunner implements CommandLineRunner {

    private final UserServiceImpl userService;
    private final TaskServiceImpl taskService;

    @Autowired
    public AppRunner(UserServiceImpl userService, TaskServiceImpl taskService) {
        this.userService = userService;
        this.taskService = taskService;
    }

    @Override
    public void run(String... args) throws Exception {

//        TaskDTO task = new TaskDTO(1L, "teste v2 que vai passar", "teste mesmo poha", Status.COMPLETED, LocalDate.now(), Priority.HIGH);
        taskService.delete(9L);

//        System.out.println(taskdto.getId() + " " + taskdto.getTitle());

    }

}
