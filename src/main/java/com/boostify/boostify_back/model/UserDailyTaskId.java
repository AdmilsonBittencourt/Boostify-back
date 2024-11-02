package com.boostify.boostify_back.model;

import java.io.Serializable;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserDailyTaskId implements Serializable {
    
    @EqualsAndHashCode.Include
    private Long user;

    @EqualsAndHashCode.Include
    private Long task;


}
