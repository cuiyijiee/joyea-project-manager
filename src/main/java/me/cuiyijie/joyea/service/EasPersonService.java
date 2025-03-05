package me.cuiyijie.joyea.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class EasPersonService extends ServiceImpl<EasPersonDao, EasPerson> {

    public EasPerson findByNumber(String fNumber) {
        return baseMapper.selectOne(new QueryWrapper<EasPerson>().eq("FNUMBER", fNumber));
    }

}
