package me.cuiyijie.joyea.model;

import lombok.Data;

@Data
public class JoyeaUserProfileResp {


    private String msg;
    private String code;
    private int status;
    private String id;

    private JoyeaUserProfile attributes;
}


