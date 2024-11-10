package com.boostify.boostify_back.repository;

import com.boostify.boostify_back.model.UserDailyTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserDailyTaskRepository extends JpaRepository<UserDailyTask, Long> {

    List<UserDailyTask> findAllByUserId(Long userId);

    List<UserDailyTask> findAllByTaskId(Long taskId);

    Optional<UserDailyTask> findByUserIdAndTaskId(Long userId, Long taskId);

}
