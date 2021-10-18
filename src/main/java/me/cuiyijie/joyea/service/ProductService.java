package me.cuiyijie.joyea.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import me.cuiyijie.joyea.dao.main.ProductDao;
import me.cuiyijie.joyea.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: yjcui3
 * @Date: 2021/10/18 11:10
 */
@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;


    public Integer insert(Product product) {
        return productDao.insert(product);
    }

    public Page<Product> list(Product product, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<Product> result = (Page<Product>) productDao.list(product);
        return result;
    }

}
