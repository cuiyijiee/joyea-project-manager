package me.cuiyijie.joyea.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import me.cuiyijie.joyea.dao.ProductScheduleDao;
import me.cuiyijie.joyea.model.ProductSchedule;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductScheduleService extends ServiceImpl<ProductScheduleDao, ProductSchedule> {

    public ProductSchedule select(ProductSchedule productSchedule) {
        return baseMapper.selectById(productSchedule.getOrderId());
    }

}
