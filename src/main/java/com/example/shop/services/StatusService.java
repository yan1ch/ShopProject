package com.example.shop.services;

import com.example.shop.dto.CreateStatusDTO;
import com.example.shop.dto.StatusDTO;
import com.example.shop.models.Status;
import com.example.shop.repositories.StatusRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusService {

    private final StatusRepository statusRepository;

    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public StatusDTO create(CreateStatusDTO dto) {
        Status status = new Status();
        status.setName(dto.getName());
        return toDTO(statusRepository.save(status));
    }

    public List<StatusDTO> getAll() {
        return statusRepository.findAll().stream().map(this::toDTO).toList();
    }

    public StatusDTO getById(Long id) {
        return toDTO(statusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Status not found")));
    }

    public StatusDTO update(Long id, CreateStatusDTO dto) {
        Status status = statusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Status not found"));
        status.setName(dto.getName());
        return toDTO(statusRepository.save(status));
    }

    public void delete(Long id) {
        statusRepository.deleteById(id);
    }

    private StatusDTO toDTO(Status s) {
        return new StatusDTO(s.getId(), s.getName());
    }
}

