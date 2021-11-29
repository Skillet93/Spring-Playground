package com.company.rs.domain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
public class TaskTestSuite {

    @PersistenceUnit
    private EntityManagerFactory emf;

    private List<Long> insertExampleData() {

        Task task1 = new Task(null, "FirstTask", "IN_PROGRESS", new HashSet<>(), new HashSet<>());
        Subtask subtask1 = new Subtask(null, "FirstSubtask", "IN_PROGRESS", task1, new HashSet<>());

        Person john = new Person(null, "John", "Doe", task1,null);
        Person mary = new Person(null, "Mary", "Kary", task1,null);

        Person ken = new Person(null, "Ken", "Gore", null, subtask1);
        task1.getPersons().addAll(List.of(john, mary));
        task1.getSubtasks().addAll(List.of(subtask1));

        subtask1.getPersons().addAll(List.of(ken));


        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(john);
        em.persist(mary);
        em.persist(ken);
        em.persist(subtask1);
        em.persist(task1);
        em.flush();
        em.getTransaction().commit();
        em.close();

        return List.of(task1.getId());
    }

    @Test
    void shouldNPlusOneProblemOccure() {
        List<Long> taskIds = insertExampleData();
        EntityManager em = emf.createEntityManager();

        List<Task> tasks =
                em.createQuery("from Task "
                                + " where id in (" + taskIds(taskIds) + ")", Task.class)
                        .getResultList();

        for (var task : tasks) {
            System.out.println("*** STEP 2 – read data from task ***");
            System.out.println(task);
            System.out.println("*** STEP 3 – read the person data ***");
            System.out.println(task.getPersons());

            for (var subtask: task.getSubtasks()) {
                System.out.println("*** STEP 4 – read the subtask ***");
                System.out.println(subtask);
                System.out.println("*** STEP 5 – read the person from subtask ***");
                System.out.println(subtask.getPersons());
            }
        }
    }

    @Test
    void nPlusOneProblemWithEntityGraph() {
        List<Long> taskIds = insertExampleData();
        EntityManager em = emf.createEntityManager();

        TypedQuery<Task> query =
                em.createQuery("from Task "
                                + " where id in (" + taskIds(taskIds) + ")", Task.class);


        EntityGraph<Task> eg = em.createEntityGraph(Task.class);
        eg.addAttributeNodes("status", "name");
        eg.addSubgraph("subtasks").addSubgraph("persons");
        eg.addSubgraph("persons").addAttributeNodes("firstName", "lastName");
        query.setHint("javax.persistence.fetchgraph", eg);

        List<Task> tasks = query.getResultList();

        for (var task : tasks) {
            System.out.println("*** STEP 2 – read data from task ***");
            System.out.println(task);
            System.out.println("*** STEP 3 – read the person data ***");
            System.out.println(task.getPersons());

            for (var subtask: task.getSubtasks()) {
                System.out.println("*** STEP 4 – read the subtask ***");
                System.out.println(subtask);
                System.out.println("*** STEP 5 – read the person from subtask ***");
                System.out.println(subtask.getPersons());
            }
        }
    }

    private String taskIds(List<Long> taskIds) {
        return taskIds.stream()
                .map(n -> "" + n)
                .collect(Collectors.joining(","));
    }
}
