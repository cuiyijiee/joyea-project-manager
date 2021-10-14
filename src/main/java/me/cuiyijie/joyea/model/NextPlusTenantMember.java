package me.cuiyijie.joyea.model;

import lombok.Data;

/**
 * @author cyj976655@gmail.com
 * @date 2021/7/18 07:39
 */
@Data
public class NextPlusTenantMember {

    private String id;
    private String name;
    private String signature;
    private String email;
    private String mobile;
    private String imageUrl;
    private Boolean activated;
}
