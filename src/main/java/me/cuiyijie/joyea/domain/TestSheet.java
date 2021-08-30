package me.cuiyijie.joyea.domain;

import lombok.Data;
import me.cuiyijie.joyea.config.UserFileType;

@Data
public class TestSheet {

    private Integer id;
    private String testName;
    private UserFileType testType;

}
