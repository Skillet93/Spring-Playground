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
public class Subtask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String status;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @OneToMany(targetEntity = Person.class, mappedBy = "subtask")
    private Set<Person> persons;
}
