package me.cuiyijie.joyea.pojo;

import com.sun.org.apache.xpath.internal.operations.Bool;
import io.swagger.models.auth.In;
import lombok.Data;

import java.util.List;

/**
 * @author cyj976655@gmail.com
 * @date 2021/7/18 07:17
 */
@Data
public class NextPlusTenant {

    private String id;
    private String name;
    private String displayName;
    private String nickName;
    private String domainName;
    private String type;
    private String imageUrl;
    private String description;
    private String parentId;
    private String owner;
    private Integer status;
    private String expiredDate;
    private Boolean activated;
    private String ext;
    private String tenantId;

    private List<NextPlusTenantMember> members;

}
