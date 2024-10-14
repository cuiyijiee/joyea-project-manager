package me.cuiyijie.joyea.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.cuiyijie.joyea.dao.EasPersonDao;
import me.cuiyijie.joyea.model.EasPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author cyj976655@gmail.com
 * @date 2022/11/21 22:00
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class EasPersonService {

    private final EasPersonDao easPersonDao;

    public EasPerson findByNumber(String fNumber) {
        return easPersonDao.selectOne(new QueryWrapper<EasPerson>().eq("FNUMBER", fNumber));
    }

}
