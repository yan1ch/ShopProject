package com.example.shop.dto;

public class CreateStatusDTO {
    private String name;

    public CreateStatusDTO() {}

    public CreateStatusDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

