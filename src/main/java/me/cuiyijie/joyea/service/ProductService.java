package me.cuiyijie.joyea.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import me.cuiyijie.joyea.dao.ProductDao;
import me.cuiyijie.joyea.model.Product;
import me.cuiyijie.joyea.pojo.request.TransProductRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @Author: cuiyijie
 * @Date: 2021/10/18 11:10
 */
@RequiredArgsConstructor
@Service
public class ProductService  extends ServiceImpl<ProductDao, Product> {

    private final SearchHistoryService searchHistoryService;

    public IPage<Product> list(String easUserId, TransProductRequest product, Integer pageNum, Integer pageSize) {
        Page<Product> productPage = new Page<>(pageNum, pageSize);
        if (StringUtils.hasLength(product.getProductName())) {
            //记录查询的关键字
            searchHistoryService.addSearchHistory(easUserId, "CACHE_PRODUCT_SEARCH_HISTORY", product.getProductName());
        }
        return product.getStatus() == 0 ? baseMapper.selectWithPage(productPage, product) : baseMapper.selectWithStatusPage(productPage, product);
    }

    public String selectCount(TransProductRequest product) {
        Integer allCount = baseMapper.selectAllCount(product.getFid());
        Integer notStartCount = baseMapper.selectNotStartCount(product.getFid());
        Integer startCount = baseMapper.selectStartCount(product.getFid());
        Integer finishCount = baseMapper.selectFinishCount(product.getFid());

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
