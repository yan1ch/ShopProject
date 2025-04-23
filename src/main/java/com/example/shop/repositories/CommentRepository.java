package com.example.shop.repositories;

import com.example.shop.models.Comment;
import com.example.shop.models.Task;
import com.example.shop.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

// CommentRepository.java
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByTask(Task task);
    Optional<Comment> findByIdAndTaskProjectUser(Long id, User user);

    @Query("SELECT c FROM Comment c JOIN FETCH c.task t JOIN FETCH t.project WHERE c.id = :id")
    Optional<Comment> findByIdWithTaskAndProject(@Param("id") Long id);
}
