package com.example.shop.services;

import com.example.shop.dto.CommentDTO;
import com.example.shop.dto.CreateCommentDTO;
import com.example.shop.models.Comment;
import com.example.shop.repositories.CommentRepository;
import com.example.shop.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;

    public CommentService(CommentRepository commentRepository, TaskRepository taskRepository) {
        this.commentRepository = commentRepository;
        this.taskRepository = taskRepository;
    }

    public CommentDTO create(CreateCommentDTO dto) {
        Comment comment = new Comment();
        comment.setText(dto.getText());
        comment.setTask(taskRepository.findById(dto.getTaskId())
                .orElseThrow(() -> new RuntimeException("Task not found")));
        return toDTO(commentRepository.save(comment));
    }

    public List<CommentDTO> getAll() {
        return commentRepository.findAll().stream().map(this::toDTO).toList();
    }

    public CommentDTO getById(Long id) {
        return toDTO(commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found")));
    }

    public CommentDTO update(Long id, CreateCommentDTO dto) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        comment.setText(dto.getText());
        comment.setTask(taskRepository.findById(dto.getTaskId())
                .orElseThrow(() -> new RuntimeException("Task not found")));
        return toDTO(commentRepository.save(comment));
    }

    public void delete(Long id) {
        commentRepository.deleteById(id);
    }

    private CommentDTO toDTO(Comment c) {
        return new CommentDTO(c.getId(), c.getText(), c.getCreatedAt(), c.getTask().getId());
    }
}

