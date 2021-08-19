package me.cuiyijie.joyea.pojo.request.urs;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TransUrsPageRequest extends TransUrsRequest {

    private Integer pageNum = 1;
    private Integer pageSize = 10;

}
