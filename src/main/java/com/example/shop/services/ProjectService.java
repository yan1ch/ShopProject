package com.example.shop.services;

import com.example.shop.dto.CreateProjectDTO;
import com.example.shop.dto.ProjectDTO;
import com.example.shop.models.Project;
import com.example.shop.models.User;
import com.example.shop.repositories.ProjectRepository;
import com.example.shop.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public ProjectService(ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    public ProjectDTO create(CreateProjectDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Project project = new Project();
        project.setName(dto.getName());
        project.setUser(user);
        return toDTO(projectRepository.save(project));
    }

    public List<ProjectDTO> getAll() {
        return projectRepository.findAll().stream().map(this::toDTO).toList();
    }

    public ProjectDTO getById(Long id) {
        return toDTO(projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found")));
    }

    public ProjectDTO update(Long id, CreateProjectDTO dto) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        project.setName(dto.getName());
        project.setUser(userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found")));
        return toDTO(projectRepository.save(project));
    }

    public void delete(Long id) {
        projectRepository.deleteById(id);
    }

    private ProjectDTO toDTO(Project p) {
        return new ProjectDTO(p.getId(), p.getName(), p.getCreatedAt(), p.getUser().getId());
    }
}
