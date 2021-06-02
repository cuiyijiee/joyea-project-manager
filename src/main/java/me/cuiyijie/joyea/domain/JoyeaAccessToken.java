package me.cuiyijie.joyea.domain;

import com.fasterxml.jackson.annotation.JsonAlias;

public class JoyeaAccessToken {

    private String msg;
    @JsonAlias("access_token")
    private String accessToken;
    private long expires;
    private int status;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public long getExpires() {
        return expires;
    }

    public void setExpires(long expires) {
        this.expires = expires;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
