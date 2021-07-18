package me.cuiyijie.joyea.pojo;

import lombok.Data;

/**
 * @author cyj976655@gmail.com
 * @date 2021/7/18 07:39
 */
@Data
public class NextPlusTenantMember {

    private String id;
    private String login;
    private String firstName;
    private String lastName;
    private String name;
    private String idCardNo;
    private String signature;
    private String email;
    private String mobile;
    private String imageUrl;
    private Boolean activated;
    private String openId;

}
