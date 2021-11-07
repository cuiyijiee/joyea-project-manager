package me.cuiyijie.joyea.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {

    private String id;
    private String name;
    private String signature;
    private String email;
    private String mobile;
    private String imageUrl;
    private Boolean activated;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
