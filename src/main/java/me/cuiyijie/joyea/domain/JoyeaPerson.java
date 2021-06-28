package me.cuiyijie.joyea.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class JoyeaPerson {

    private String id;
    private String number;
    private String name;
    private String email;
    private String gender;
    private LocalDateTime birthday;
    private String address;
    private String mobile;
    private String idCard;
    private String jobNumber;

}
