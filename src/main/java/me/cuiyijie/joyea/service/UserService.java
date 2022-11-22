package me.cuiyijie.joyea.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import me.cuiyijie.joyea.dao.UserDao;
import me.cuiyijie.joyea.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User findByPersonId(String personId) {
        return userDao.selectOne(new QueryWrapper<User>().eq("FPERSONID", personId));
    }

}
