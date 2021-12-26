package me.cuiyijie.joyea.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Sheet {
    private Integer id;
    private String name;
    private String category;
    private String remark;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
