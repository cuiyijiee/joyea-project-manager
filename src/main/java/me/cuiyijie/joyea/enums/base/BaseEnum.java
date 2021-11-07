package me.cuiyijie.joyea.enums.base;

import com.fasterxml.jackson.annotation.JsonValue;

public interface BaseEnum {

    @JsonValue
    String getKey();

    String getValue();

}
