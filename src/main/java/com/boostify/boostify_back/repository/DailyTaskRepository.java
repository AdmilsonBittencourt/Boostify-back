package com.boostify.boostify_back.repository;

import com.boostify.boostify_back.model.DailyTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyTaskRepository extends JpaRepository<DailyTask, Integer> {
}
