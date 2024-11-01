package com.boostify.boostify_back.model.dto;

import com.boostify.boostify_back.enums.Priority;
import com.boostify.boostify_back.enums.Status;
import com.boostify.boostify_back.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {

    private Long id;
    private Long idUser;
    private String title;
    private String description;
    private Status status;
    private LocalDate creationDate;
    private Priority priority;

    public TaskDTO(Long idUser, String title, String description, Status status, LocalDate creationDate, Priority priority) {
        this.idUser = idUser;
        this.title = title;
        this.description = description;
        this.status = status;
        this.creationDate = creationDate;
        this.priority = priority;
    }
}
