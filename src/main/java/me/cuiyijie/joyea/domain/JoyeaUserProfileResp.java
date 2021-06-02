package me.cuiyijie.joyea.domain;

public class JoyeaUserProfileResp {


    private String msg;
    private String code;
    private int status;
    private String id;

    private JoyeaUserProfile attributes;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public JoyeaUserProfile getAttributes() {
        return attributes;
    }

    public void setAttributes(JoyeaUserProfile attributes) {
        this.attributes = attributes;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}


