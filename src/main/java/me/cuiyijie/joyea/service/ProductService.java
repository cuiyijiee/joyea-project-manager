package me.cuiyijie.joyea.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.cuiyijie.joyea.dao.ProductDao;
import me.cuiyijie.joyea.model.Product;
import me.cuiyijie.joyea.pojo.request.TransProductRequest;
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

    public IPage<Product> list(TransProductRequest product, Integer pageNum, Integer pageSize) {
        Page<Product> productPage = new Page<>(pageNum, pageSize);
        return productDao.selectWithPage(productPage, product);
    }

    public String selectCount(TransProductRequest product) {
        Integer allCount = productDao.selectAllCount(product.getXmId());
        Integer notStartCount = productDao.selectNotStartCount(product.getXmId());
        Integer startCount = productDao.selectStartCount(product.getXmId());
        Integer finishCount = productDao.selectFinishCount(product.getXmId());

        return String.format("%s_%s_%s_%s", allCount, notStartCount, startCount, finishCount);
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
