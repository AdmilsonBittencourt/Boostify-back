package com.boostify.boostify_back.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.boostify.boostify_back.enums.Priority;
import com.boostify.boostify_back.enums.Status;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(name = "creation_date", nullable = false)
    private LocalDate creationDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Priority priority;

    @OneToMany(mappedBy = "task", orphanRemoval = true)
    private List<UserDailyTask> dailyTasks = new ArrayList<>();

    public Task(User user, String title, String description, Status status, LocalDate creationDate, Priority priority) {
        this.user = user;
        this.title = title;
        this.description = description;
        this.status = status;
        this.creationDate = creationDate;
        this.priority = priority;
    }
}
