package me.cuiyijie.joyea.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: cuiyijie
 * @Date: 2021/12/22 16:11
 */
@Data
public class SheetData {

    private Integer id;
    private Integer stageRelId;
    private Integer sheetId;
    private Integer columnId;
    private Integer rowIndex;
    private String columnValue;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
