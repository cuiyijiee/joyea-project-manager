package me.cuiyijie.joyea.service;

import lombok.RequiredArgsConstructor;
import me.cuiyijie.joyea.dao.ProductScheduleDao;
import me.cuiyijie.joyea.model.ProductSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductScheduleService {

    private final ProductScheduleDao productScheduleDao;

    public ProductSchedule select(ProductSchedule productSchedule) {
        return productScheduleDao.selectById(productSchedule.getOrderId());
    }

}
