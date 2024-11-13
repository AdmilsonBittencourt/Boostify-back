package com.boostify.boostify_back.service.task;

import com.boostify.boostify_back.controller.dto.TaskStatusDTO;
import com.boostify.boostify_back.model.Task;
import com.boostify.boostify_back.model.User;
import com.boostify.boostify_back.controller.dto.TaskDTO;
import com.boostify.boostify_back.service.BaseService;

import java.util.List;

public interface TaskService extends BaseService<TaskDTO, Long> {

    Task checkTaskExists(Long id);

    Task taskExistsForUserWithTitle(String title, User user);

    List<TaskDTO> findAllByUserId(Long id);

    TaskDTO updateStatus(Long id, TaskStatusDTO taskDTO);
}
