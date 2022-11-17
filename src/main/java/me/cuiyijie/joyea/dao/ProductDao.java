package me.cuiyijie.joyea.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseMapper;
import me.cuiyijie.joyea.model.Product;
import me.cuiyijie.joyea.pojo.request.TransProductRequest;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author: cuiyijie
 * @Date: 2021/10/18 11:04
 */
@Repository
public interface ProductDao extends MPJBaseMapper<Product> {

    IPage<Product> selectWithPage(IPage<Product> page, @Param("item") TransProductRequest product);

    Integer selectAllCount(@Param("fid") String fid);

    Integer selectNotStartCount(@Param("fid") String fid);

    Integer selectStartCount(@Param("fid") String fid);

    Integer selectFinishCount(@Param("fid") String fid);

}
