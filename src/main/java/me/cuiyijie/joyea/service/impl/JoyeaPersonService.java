package me.cuiyijie.joyea.service.impl;

import me.cuiyijie.joyea.dao.joyea.JoyeaPersonDao;
import me.cuiyijie.joyea.domain.JoyeaPerson;
import me.cuiyijie.joyea.service.IJoyeaPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JoyeaPersonService implements IJoyeaPersonService {

    @Autowired
    private JoyeaPersonDao joyeaPersonDao;

    @Override
    public List<JoyeaPerson> list(JoyeaPerson joyeaPerson) {
        return joyeaPersonDao.list(joyeaPerson);
    }
}
