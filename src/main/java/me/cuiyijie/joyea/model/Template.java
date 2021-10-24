package me.cuiyijie.joyea.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Template {

    private Integer id;
    private Integer pid;
    private String name;
    private String levelType;
    private Boolean isRoot;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
