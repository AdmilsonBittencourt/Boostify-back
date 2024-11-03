package com.boostify.boostify_back.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDailyTaskDTO {

    private Long idUser;
    private Long idTask;
}
