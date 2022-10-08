package me.cuiyijie.joyea.pojo.request;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransBasePageResponse extends TransBaseResponse {

    private Long pageNum;
    private Long pageSize;
    private Long total;
    private Boolean hasMore;

    public TransBasePageResponse(Page page) {
        this.setCode("0");
        this.setList(page.getRecords());
        this.setPageNum(page.getCurrent());
        this.setPageSize(page.getSize());
        this.setTotal(page.getTotal());
        this.setHasMore(page.getCurrent() < page.getPages());
    }

    public TransBasePageResponse(IPage page) {
        this.setCode("0");
        this.setList(page.getRecords());
        this.setPageNum(page.getCurrent());
        this.setPageSize(page.getSize());
        this.setTotal(page.getTotal());
        this.setHasMore(page.getCurrent() < page.getPages());
    }
}
