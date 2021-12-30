package me.cuiyijie.joyea.service;

import me.cuiyijie.joyea.dao.UserDao;
import me.cuiyijie.joyea.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public List<User> listAll(User user) {
        return userDao.listAll(user);
    }

    public Integer update(User user) {
        return userDao.update(user);
    }

    public Integer insert(User user) {
        return userDao.insert(user);
    }

    public Integer delete(User user) {
        return userDao.delete(user);
    }

}
