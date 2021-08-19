package me.cuiyijie.joyea.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import me.cuiyijie.joyea.dao.joyea.JoyeaUrsDao;
import me.cuiyijie.joyea.domain.JoyeaUrs;
import me.cuiyijie.joyea.service.IJoyeaUrsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JoyeaUrsServiceImpl implements IJoyeaUrsService {

    @Autowired
    private JoyeaUrsDao joyeaUrsDao;

    @Override
    public PageInfo<JoyeaUrs> selectByProjectNumber(String projectNumber, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<JoyeaUrs> result = joyeaUrsDao.selectByProjectNumber(projectNumber);
        return new PageInfo<>(result);
    }
}
