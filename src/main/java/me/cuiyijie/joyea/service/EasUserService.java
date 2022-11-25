package me.cuiyijie.joyea.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import me.cuiyijie.joyea.dao.EasUserDao;
import me.cuiyijie.joyea.model.EasUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EasUserService {

    @Autowired
    private EasUserDao userDao;

    public EasUser findByPersonId(String personId) {
        return userDao.selectOne(new QueryWrapper<EasUser>().eq("FPERSONID", personId));
    }

    public EasUser findById(String fid) {
        return userDao.selectById(fid);
    }

    public EasUser findByNumber(String fNumber) {
        return userDao.selectOne(new QueryWrapper<EasUser>().eq("FNUMBER",fNumber));
    }

}
