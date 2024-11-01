package com.boostify.boostify_back.repository;

import com.boostify.boostify_back.model.Task;
import com.boostify.boostify_back.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

//    @Modifying
//    @Query("DELETE FROM tasks t WHERE t.creation_date < :currentDate AND t.id NOT IN (SELECT udt.id_user FROM user_daily_tasks udt)")
//    void deleteTasksFromPreviousDays(LocalDate currentDate);

    Optional<Task> findByTitleAndUser(String title, User user);
}
