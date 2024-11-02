package com.boostify.boostify_back.service.task;

import com.boostify.boostify_back.model.Task;
import com.boostify.boostify_back.model.User;
import com.boostify.boostify_back.controller.dto.TaskDTO;
import com.boostify.boostify_back.repository.TaskRepository;
import com.boostify.boostify_back.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService{

    private final TaskRepository taskRepository;
    private final UserService userService;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, UserService userService) {
        this.taskRepository = taskRepository;
        this.userService = userService;
    }

    @Override
    public TaskDTO create(TaskDTO taskDTO) {

        User user = userService.checkUserExists(taskDTO.getIdUser());

        taskExistsForUserWithTitle(taskDTO.getTitle(), user);

        Task save = taskRepository.save(
                new Task(
                        user,
                        taskDTO.getTitle(),
                        taskDTO.getDescription(),
                        taskDTO.getStatus(),
                        taskDTO.getCreationDate(),
                        taskDTO.getPriority()
                )
        );

        return new TaskDTO(
                save.getId(),
                save.getUser().getId(),
                save.getTitle(),
                save.getDescription(),
                save.getStatus(),
                save.getCreationDate(),
                save.getPriority()
        );
    }

    @Override
    public TaskDTO findById(Long id) {

        Task task = checkTaskExists(id);

        return new TaskDTO(
                task.getId(),
                task.getUser().getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getCreationDate(),
                task.getPriority()
        );
    }

    @Override
    public TaskDTO update(Long id, TaskDTO taskDTO) {

        Task task = checkTaskExists(id);

        taskExistsForUserWithTitle(taskDTO.getTitle(), task.getUser());

        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setPriority(taskDTO.getPriority());

        Task save = taskRepository.save(task);

        return new TaskDTO(
                save.getId(),
                save.getUser().getId(),
                save.getTitle(),
                save.getDescription(),
                save.getStatus(),
                save.getCreationDate(),
                save.getPriority()
        );
    }

    @Override
    public void delete(Long id) {
        checkTaskExists(id);
        taskRepository.deleteById(id);
    }

    @Override
    public Task checkTaskExists(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new RuntimeException("task not found"));
    }

    @Override
    public void taskExistsForUserWithTitle(String title, User user) {
        Optional<Task> taskByTitleAndUser = taskRepository.findByTitleAndUser(title, user);

        if(taskByTitleAndUser.isPresent()) {
            throw new RuntimeException("title already exists");
        }
    }
}
