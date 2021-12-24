package me.cuiyijie.joyea.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SysFileUpload {

    private String id;
    private String originFileName;
    private String fileSuffix;
    private Integer fileType;
    private String createdBy;
    private LocalDateTime createdAt = LocalDateTime.now();

    public String getFullName() {
        return String.format("%s.%s", id, fileSuffix);
    }

}
