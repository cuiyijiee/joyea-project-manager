package me.cuiyijie.joyea.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckItemRecord {

    private Integer id;
    private Integer stageRelId;
    private Integer checkItemId;
    private String operationId;
    private String checkRecord;
    private LocalDateTime createdAt;

}
