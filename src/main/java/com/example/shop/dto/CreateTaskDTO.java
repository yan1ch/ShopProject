package com.example.shop.dto;

import java.time.LocalDateTime;

public class CreateTaskDTO {
    private String title;
    private String description;
    private Long statusId;
    private Long projectId;
    private LocalDateTime deadline;

    public CreateTaskDTO() {}

    public CreateTaskDTO(String title, String description, Long statusId, Long projectId, LocalDateTime deadline) {
        this.title = title;
        this.description = description;
        this.statusId = statusId;
        this.projectId = projectId;
        this.deadline = deadline;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }
}

