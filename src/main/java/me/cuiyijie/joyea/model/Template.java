package me.cuiyijie.joyea.model;

import lombok.Data;
import me.cuiyijie.joyea.enums.TemplateLevelType;

import java.time.LocalDateTime;

@Data
public class Template {

    private Integer id;
    private Integer pid;
    private String name;
    private TemplateLevelType levelType;
    private Boolean isRoot;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public String getLevelTypeValue(){
        return levelType == null ? "" :levelType.getValue();
    }

}
