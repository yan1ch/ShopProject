package com.example.shop.repositories;

import com.example.shop.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

// StatusRepository.java
public interface StatusRepository extends JpaRepository<Status, Long> {
    Optional<Status> findByName(String name);

    @Query("SELECT s FROM Status s LEFT JOIN FETCH s.tasks WHERE s.id = :id")
    Optional<Status> findByIdWithTasks(@Param("id") Long id);
}
