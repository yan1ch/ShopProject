package com.example.shop.dto;

import java.time.LocalDateTime;

public class CommentDTO {
    private Long id;
    private String text;
    private LocalDateTime createdAt;
    private Long taskId;

    public CommentDTO() {}

    public CommentDTO(Long id, String text, LocalDateTime createdAt, Long taskId) {
        this.id = id;
        this.text = text;
        this.createdAt = createdAt;
        this.taskId = taskId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }
}
