package me.cuiyijie.joyea.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.cuiyijie.joyea.dao.ProductDao;
import me.cuiyijie.joyea.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @Author: cuiyijie
 * @Date: 2021/10/18 11:10
 */
@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    public Page<Product> list(Product product, Integer pageNum, Integer pageSize) {
        Page<Product> productPage = new Page<>(pageNum, pageSize);
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        if (StringUtils.hasLength(product.getProjectId())) {
            queryWrapper.eq("XMID", product.getProjectId());
        }
        if (StringUtils.hasLength(product.getProductName())) {
            queryWrapper.and(productQueryWrapper -> {
                productQueryWrapper.like("ORDERNUMBER", product.getProductName())
                        .or()
                        .like("PRODUCTNUMBER", product.getProductName());
            });
        }
        return productDao.selectPage(productPage, queryWrapper);
    }

    public Integer insert(Product product) {
        return null;
    }

    public Integer delete(Integer id) {
        return null;
    }

    public Integer update(Product product) {
        return null;
    }

}
