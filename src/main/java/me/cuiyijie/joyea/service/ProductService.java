package me.cuiyijie.joyea.service;

import me.cuiyijie.joyea.dao.ProductDao;
import me.cuiyijie.joyea.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: cuiyijie
 * @Date: 2021/10/18 11:10
 */
@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;


    public Integer insert(Product product) {
        return productDao.insert(product);
    }

    public List<Product> list(Product product) {
        return productDao.list(product);
    }

    public Integer delete(Integer id){
        return productDao.delete(id);
    }

    public Integer update(Product product){
        return productDao.update(product);
    }

}
