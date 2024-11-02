package com.boostify.boostify_back.service.user_daily_task;

import com.boostify.boostify_back.model.dto.UserDailyTaskDTO;

import java.util.List;

public interface UserDailyTaskService {

    UserDailyTaskDTO create(UserDailyTaskDTO userDailyTaskDTO);

    UserDailyTaskDTO findByUserIdAndTaskId(Long userId, Long taskId);

    List<UserDailyTaskDTO> findAllByUserId(Long userId);

    List<UserDailyTaskDTO> findAllByTaskId(Long taskId);

    void delete(Long userId, Long taskId);
}
