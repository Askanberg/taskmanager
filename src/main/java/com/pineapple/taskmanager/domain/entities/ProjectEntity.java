package com.pineapple.taskmanager.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "projects")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "projects_id_seq")
    private long id;

    private String name;

//    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
//   private List<TaskEntity.java> taskEntities;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProjectEntity)) return false;
        ProjectEntity projectEntity = (ProjectEntity) o;
        return id == projectEntity.id && Objects.equals(name, projectEntity.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

}
