package com.boostify.boostify_back.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
public class DailyTaskId implements Serializable {
    
    @EqualsAndHashCode.Include
    private Long userId;

    @EqualsAndHashCode.Include
    private Long taskId;


}
