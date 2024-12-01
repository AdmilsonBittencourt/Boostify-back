package com.boostify.boostify_back.controller.dto;

import java.time.LocalDate;

import com.boostify.boostify_back.enums.Priority;
import com.boostify.boostify_back.enums.Status;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {

    private Long id;

    @NotNull
    private Long idUser;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    private Status status;
    private LocalDate creationDate;

    @NotNull
    private Priority priority;

    @NotNull
    private Boolean isDaily;

    public TaskDTO(Long idUser, String title, String description, Priority priority, Boolean isDaily) {
        this.idUser = idUser;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.isDaily = isDaily;
    }
}
