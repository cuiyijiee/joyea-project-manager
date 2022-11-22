package me.cuiyijie.joyea.service;

import me.cuiyijie.joyea.dao.ProductScheduleDao;
import me.cuiyijie.joyea.model.ProductSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductScheduleService {

    @Autowired
    private ProductScheduleDao productScheduleDao;

    public ProductSchedule select(ProductSchedule productSchedule) {
        return productScheduleDao.selectById(productSchedule.getOrderId());
    }

}
