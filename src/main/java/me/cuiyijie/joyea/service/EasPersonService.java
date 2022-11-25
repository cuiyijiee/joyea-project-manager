package me.cuiyijie.joyea.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import me.cuiyijie.joyea.dao.EasPersonDao;
import me.cuiyijie.joyea.model.EasPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author cyj976655@gmail.com
 * @date 2022/11/21 22:00
 */

@Service
public class EasPersonService {


    @Autowired
    private EasPersonDao easPersonDao;

    public EasPerson findByNumber(String fNumber) {
        return easPersonDao.selectOne(new QueryWrapper<EasPerson>().eq("FNUMBER", fNumber));
    }

}
