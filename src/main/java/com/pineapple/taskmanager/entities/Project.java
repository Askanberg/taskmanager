package com.pineapple.taskmanager.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "projects")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "projects_id_seq")
    private long id;

    private String name;

    @OneToMany(mappedBy = "project_id", cascade = CascadeType.ALL)
    private List<Task> tasks;

}
