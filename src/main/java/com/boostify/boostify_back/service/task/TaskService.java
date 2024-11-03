package com.boostify.boostify_back.service.task;

import com.boostify.boostify_back.model.Task;
import com.boostify.boostify_back.model.User;
import com.boostify.boostify_back.controller.dto.TaskDTO;
import com.boostify.boostify_back.service.BaseService;

public interface TaskService extends BaseService<TaskDTO, Long> {

    Task checkTaskExists(Long id);

    void taskExistsForUserWithTitle(String title, User user);
}
