package me.cuiyijie.joyea.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import me.cuiyijie.joyea.dao.PersonDao;
import me.cuiyijie.joyea.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author cyj976655@gmail.com
 * @date 2022/11/21 22:00
 */

@Service
public class PersonService {


    @Autowired
    private PersonDao personDao;

    public Person findByNumber(String fNumber) {
        return personDao.selectOne(new QueryWrapper<Person>().eq("FNUMBER", fNumber));
    }

}
