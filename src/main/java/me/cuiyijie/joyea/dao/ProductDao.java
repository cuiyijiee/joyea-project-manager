package me.cuiyijie.joyea.dao;

import io.swagger.models.auth.In;
import me.cuiyijie.joyea.model.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: cuiyijie
 * @Date: 2021/10/18 11:04
 */
@Repository
public interface ProductDao {

    Integer insert(@Param("item") Product product);

    List<Product> list(@Param("item") Product product);

    Integer delete(@Param("id") Integer id);

    Integer update(@Param("item") Product product);
}
