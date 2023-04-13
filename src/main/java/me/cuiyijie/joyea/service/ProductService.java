package me.cuiyijie.joyea.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.cuiyijie.joyea.dao.ProductDao;
import me.cuiyijie.joyea.model.Product;
import me.cuiyijie.joyea.pojo.request.TransProductRequest;
import org.checkerframework.checker.units.qual.A;
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

    @Autowired
    private SearchHistoryService searchHistoryService;

    public IPage<Product> list(String easUserId, TransProductRequest product, Integer pageNum, Integer pageSize) {
        Page<Product> productPage = new Page<>(pageNum, pageSize);
        if(StringUtils.hasLength(product.getProductName())) {
            //记录查询的关键字
            if(StringUtils.hasLength(product.getProductName())) {
                searchHistoryService.addSearchHistory(easUserId,"PRODUCT",product.getProductName());
            }
        }
        return productDao.selectWithPage(productPage, product);
    }

    public String selectCount(TransProductRequest product) {
        Integer allCount = productDao.selectAllCount(product.getFid());
        Integer notStartCount = productDao.selectNotStartCount(product.getFid());
        Integer startCount = productDao.selectStartCount(product.getFid());
        Integer finishCount = productDao.selectFinishCount(product.getFid());

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
