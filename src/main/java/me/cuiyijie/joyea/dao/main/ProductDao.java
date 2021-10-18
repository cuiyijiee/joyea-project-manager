package me.cuiyijie.joyea.dao.main;

import me.cuiyijie.joyea.model.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: yjcui3
 * @Date: 2021/10/18 11:04
 */
@Repository
public interface ProductDao {

    Integer insert(@Param("item") Product product);

    List<Product> list(@Param("item") Product product);

}
