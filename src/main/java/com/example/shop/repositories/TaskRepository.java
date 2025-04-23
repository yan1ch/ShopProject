package com.example.shop.repositories;

import com.example.shop.models.Project;
import com.example.shop.models.Status;
import com.example.shop.models.Task;
import com.example.shop.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

// TaskRepository.java
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByProject(Project project);
    List<Task> findByStatus(Status status);
    Optional<Task> findByIdAndProjectUser(Long id, User user);

    @Query("SELECT t FROM Task t JOIN FETCH t.project JOIN FETCH t.status WHERE t.id = :id")
    Optional<Task> findByIdWithProjectAndStatus(@Param("id") Long id);

    @Query("SELECT t FROM Task t JOIN FETCH t.project p WHERE p.user = :user")
    List<Task> findAllByUser(@Param("user") User user);
}
