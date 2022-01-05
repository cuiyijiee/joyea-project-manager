package me.cuiyijie.joyea.pojo;

import lombok.Data;

import java.util.List;

/**
 * @Author: yjcui3
 * @Date: 2022/1/5 10:12
 */
@Data
public class TransNotifyRequest {

    private List<String> ids;
    private Integer checkItemId;

}
