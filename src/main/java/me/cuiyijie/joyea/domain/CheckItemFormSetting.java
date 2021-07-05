package me.cuiyijie.joyea.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.beans.Transient;

@Data
public class CheckItemFormSetting {


    private Integer id;

    @JsonIgnore
    private String checkItemId;
    private Integer seq;
    private String columnName;

}
