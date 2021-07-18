package me.cuiyijie.joyea.pojo;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class NextPlusNotificationRequest {

    @JsonAlias("eappId")
    private String eAppId = "2c948b3375315f5a017673df99be00bc";

    private List<String> targetIds = new ArrayList<>();

    private NextPlusNotificationMessage message;

    @Data
    public static class NextPlusNotificationMessage {
        private String content;
        private String title;
        private String subTitle;
        private String linkUrl;
    }
}
