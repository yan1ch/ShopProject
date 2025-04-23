package com.example.shop.dto;

import com.example.shop.models.Project;
import com.example.shop.models.User;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class ProjectDTO {
    private Long id;
    private String name;
    private LocalDateTime createdAt;
    private Long userId;

    public ProjectDTO(Long id, String name, LocalDateTime createdAt, Long userId) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public ProjectDTO() {}
}
