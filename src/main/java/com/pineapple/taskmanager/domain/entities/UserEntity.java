package com.pineapple.taskmanager.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;
    private String password;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<TaskEntity.java> taskEntities;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEntity)) return false;
        UserEntity userEntity = (UserEntity) o;
        return id == userEntity.id && Objects.equals(username, userEntity.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username);
    }
}
