package me.cuiyijie.joyea.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductDao productDao;
    private final SearchHistoryService searchHistoryService;

    public IPage<Product> list(String easUserId, TransProductRequest product, Integer pageNum, Integer pageSize) {
        Page<Product> productPage = new Page<>(pageNum, pageSize);
        if (StringUtils.hasLength(product.getProductName())) {
            //记录查询的关键字
            searchHistoryService.addSearchHistory(easUserId, "CACHE_PRODUCT_SEARCH_HISTORY", product.getProductName());
        }
        return product.getStatus() == 0 ? productDao.selectWithPage(productPage, product) : productDao.selectWithStatusPage(productPage, product);
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
