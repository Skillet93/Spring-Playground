package com.company.rs.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String status;

    @OneToMany(targetEntity = Person.class, mappedBy = "task")
    private Set<Person> persons;

    @OneToMany(targetEntity = Subtask.class, mappedBy = "task")
    private Set<Subtask> subtasks;

}