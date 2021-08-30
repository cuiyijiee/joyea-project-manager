package me.cuiyijie.joyea.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SysFileUpload {

    private Integer id;
    private String originFileName;
    private String fileSaveId;
    private String fileSuffix;
    private Integer fileType;
    private String createdBy;
    private LocalDateTime createdAt = LocalDateTime.now();

    public String getFullName() {
        return String.format("%s.%s", fileSaveId, fileSuffix);
    }

}
