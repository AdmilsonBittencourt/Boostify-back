package com.boostify.boostify_back.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "daily_tasks")
@IdClass(DailyTaskId.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DailyTask {
    
    @Id 
    @Column(name = "id_user")
    private Integer idUser;

    @Id 
    @Column(name = "id_tasks")
    private Integer idTask;

    @ManyToOne
    @MapsId("idUser")
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @MapsId("idTask")
    @JoinColumn(name = "id_tasks", referencedColumnName = "id")
    private Task task;



}
