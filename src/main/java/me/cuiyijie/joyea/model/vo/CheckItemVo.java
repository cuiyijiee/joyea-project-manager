package me.cuiyijie.joyea.model.vo;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.cuiyijie.joyea.domain.SysFileUpload;
import me.cuiyijie.joyea.model.CheckItem;
import me.cuiyijie.joyea.model.CheckItemTag;

import java.util.List;

/**
 * @Author: cuiyijie
 * @Date: 2021/11/8 14:03
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CheckItemVo extends CheckItem {

    private Integer pageSize = 10;
    private Integer pageNum = 1;

    private List<CheckItemTag> checkItemTags;

    private Integer pid;
    private List<Integer> ids;

    @ApiModelProperty(value = "产品标签搜索")
    private String productTagText;
    @ApiModelProperty(value = "工序标签搜索")
    private String operationTagText;

}
