package com.boostify.boostify_back.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_daily_tasks")
@IdClass(UserDailyTaskId.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDailyTask {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_tasks", nullable = false)
    private Task task;

}
