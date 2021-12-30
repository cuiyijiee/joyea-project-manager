package me.cuiyijie.joyea.pojo.request;


import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransBasePageResponse extends TransBaseResponse {

    private Integer pageNum;
    private Integer pageSize;
    private Long total;


    public TransBasePageResponse(PageInfo pageInfo) {
        this.setCode("0");
        this.setList(pageInfo.getList());
        this.setPageNum(pageInfo.getPageNum());
        this.setPageSize(pageInfo.getPageSize());
        this.setTotal(pageInfo.getTotal());
    }

}
