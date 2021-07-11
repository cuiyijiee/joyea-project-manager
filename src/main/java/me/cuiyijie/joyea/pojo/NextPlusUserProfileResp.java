package me.cuiyijie.joyea.pojo;

import lombok.Data;

/**
 * @author cyj976655@gmail.com
 * @date 2021/7/11 20:47
 */
@Data
public class NextPlusUserProfileResp {

    //当前id
    private String id;
    //当前用户名
    private String name;
    //用户手机号
    private String phone;
    //一森用户id
    private String ytmId;
    //一森openid
    private String ytmOpenId;
    //一森组织id
    private String tenantId;

}
