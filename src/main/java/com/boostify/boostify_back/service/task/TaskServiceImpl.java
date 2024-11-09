package com.boostify.boostify_back.service.task;

import com.boostify.boostify_back.enums.Status;
import com.boostify.boostify_back.exceptions.BadRequestException;
import com.boostify.boostify_back.exceptions.NotFoundException;
import com.boostify.boostify_back.model.Task;
import com.boostify.boostify_back.model.User;
import com.boostify.boostify_back.controller.dto.TaskDTO;
import com.boostify.boostify_back.repository.TaskRepository;
import com.boostify.boostify_back.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
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

        Task titleExists = taskExistsForUserWithTitle(taskDTO.getTitle(), user);

        if(titleExists != null) {
            throw new BadRequestException("title already exists");
        }

        Task save = taskRepository.save(
                new Task(
                        user,
                        taskDTO.getTitle(),
                        taskDTO.getDescription(),
                        Status.PENDING,
                        LocalDate.now(),
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
    public List<TaskDTO> findAllByUserId(Long id) {

        User user = userService.checkUserExists(id);

        return taskRepository.findAllByUser(user)
                .stream()
                .map(task -> new TaskDTO(
                        task.getId(),
                        task.getUser().getId(),
                        task.getTitle(),
                        task.getDescription(),
                        task.getStatus(),
                        task.getCreationDate(),
                        task.getPriority()
                )).toList();
    }

    @Override
    public TaskDTO update(Long id, TaskDTO taskDTO) {

        Task task = checkTaskExists(id);

        var titleExists = taskExistsForUserWithTitle(taskDTO.getTitle(), task.getUser());

        if(!titleExists.getId().equals(id)) {
            throw new BadRequestException("title already exists");
        }

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
    public TaskDTO updateStatus(Long id, TaskDTO taskDTO) {

        Task task = checkTaskExists(id);
        task.setStatus(taskDTO.getStatus());

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
    public Task checkTaskExists(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new NotFoundException("task not found"));
    }

    @Override
    public Task taskExistsForUserWithTitle(String title, User user) {

        return taskRepository.findByTitleAndUser(title, user).orElse(null);

    }
}
