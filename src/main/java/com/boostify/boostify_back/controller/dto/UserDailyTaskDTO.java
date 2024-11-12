package com.boostify.boostify_back.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDailyTaskDTO {

    @NotNull
    private Long idUser;

    @NotNull
    private Long idTask;
}
