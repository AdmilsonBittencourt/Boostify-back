package com.boostify.boostify_back.service.user_daily_task;

import com.boostify.boostify_back.model.Task;
import com.boostify.boostify_back.model.User;
import com.boostify.boostify_back.model.UserDailyTask;
import com.boostify.boostify_back.controller.dto.UserDailyTaskDTO;
import com.boostify.boostify_back.repository.UserDailyTaskRepository;
import com.boostify.boostify_back.service.task.TaskService;
import com.boostify.boostify_back.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDailyTaskImpl implements UserDailyTaskService{

    private final UserDailyTaskRepository repository;
    private final UserService userService;
    private final TaskService taskService;

    @Autowired
    public UserDailyTaskImpl(UserDailyTaskRepository repository, UserService userService, TaskService taskService) {
        this.repository = repository;
        this.userService = userService;
        this.taskService = taskService;
    }

    @Override
    public UserDailyTaskDTO create(UserDailyTaskDTO userDailyTaskDTO) {

        User user = userService.checkUserExists(userDailyTaskDTO.getIdUser());
        Task task = taskService.checkTaskExists(userDailyTaskDTO.getIdTask());

        UserDailyTask save = repository.save(new UserDailyTask(user, task));

        return new UserDailyTaskDTO(save.getUser().getId(), save.getTask().getId());
    }

    @Override
    public UserDailyTaskDTO findByUserIdAndTaskId(Long userId, Long taskId) {

        return repository.findByUserIdAndTaskId(userId, taskId)
                .map(x -> new UserDailyTaskDTO(x.getUser().getId(), x.getTask().getId()))
                .orElseThrow(() -> new RuntimeException("user_daily_task not found"));
    }

    @Override
    public List<UserDailyTaskDTO> findAllByUserId(Long userId) {

        return repository.findAllByUserId(userId)
                .stream().map(x -> new UserDailyTaskDTO(x.getUser().getId(), x.getTask().getId())).toList();
    }

    @Override
    public List<UserDailyTaskDTO> findAllByTaskId(Long taskId) {
        return repository.findAllByTaskId(taskId)
                .stream().map(x -> new UserDailyTaskDTO(x.getUser().getId(), x.getTask().getId())).toList();
    }

    @Override
    public void delete(Long userId, Long taskId) {

        UserDailyTask userDailyTaskNotFound = repository.findByUserIdAndTaskId(userId, taskId)
                .orElseThrow(() -> new RuntimeException("user_daily_task not found"));

        repository.delete(userDailyTaskNotFound);
    }
}
