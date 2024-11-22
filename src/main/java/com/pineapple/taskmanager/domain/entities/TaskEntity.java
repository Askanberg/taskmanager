package com.pineapple.taskmanager.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tasks")
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String description;

    private boolean completed;
    //private LocalDate dueDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;
//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "project_id")
//    private ProjectEntity projectEntity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaskEntity)) return false;
        TaskEntity taskEntity = (TaskEntity) o;
        return id == taskEntity.id && Objects.equals(title, taskEntity.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }
}
