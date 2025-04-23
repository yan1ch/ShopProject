package com.example.shop.services;

import com.example.shop.dto.CreateTaskDTO;
import com.example.shop.dto.TaskDTO;
import com.example.shop.models.Task;
import com.example.shop.repositories.ProjectRepository;
import com.example.shop.repositories.StatusRepository;
import com.example.shop.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final StatusRepository statusRepository;

    public TaskService(TaskRepository taskRepository, ProjectRepository projectRepository, StatusRepository statusRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.statusRepository = statusRepository;
    }

    public TaskDTO create(CreateTaskDTO dto) {
        Task task = mapToEntity(dto);
        return toDTO(taskRepository.save(task));
    }

    public List<TaskDTO> getAll() {
        return taskRepository.findAll().stream().map(this::toDTO).toList();
    }

    public TaskDTO getById(Long id) {
        return toDTO(taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found")));
    }

    public TaskDTO update(Long id, CreateTaskDTO dto) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setDeadline(dto.getDeadline());
        task.setProject(projectRepository.findById(dto.getProjectId())
                .orElseThrow(() -> new RuntimeException("Project not found")));
        task.setStatus(statusRepository.findById(dto.getStatusId())
                .orElseThrow(() -> new RuntimeException("Status not found")));
        return toDTO(taskRepository.save(task));
    }

    public void delete(Long id) {
        taskRepository.deleteById(id);
    }

    private Task mapToEntity(CreateTaskDTO dto) {
        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setDeadline(dto.getDeadline());
        task.setProject(projectRepository.findById(dto.getProjectId())
                .orElseThrow(() -> new RuntimeException("Project not found")));
        task.setStatus(statusRepository.findById(dto.getStatusId())
                .orElseThrow(() -> new RuntimeException("Status not found")));
        return task;
    }

    private TaskDTO toDTO(Task t) {
        return new TaskDTO(
                t.getId(), t.getTitle(), t.getDescription(), t.getStatus().getId(),
                t.getProject().getId(), t.getCreatedAt(), t.getDeadline()
        );
    }
}
