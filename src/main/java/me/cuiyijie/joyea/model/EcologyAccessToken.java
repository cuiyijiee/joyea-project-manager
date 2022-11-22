package me.cuiyijie.joyea.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public class EcologyAccessToken {

    private String msg;
    @JsonAlias("access_token")
    private String accessToken;
    private long expires;
    private int status;

}