package me.cuiyijie.joyea.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import me.cuiyijie.joyea.dao.joyea.JoyeaUrsDao;
import me.cuiyijie.joyea.dao.main.JoyeaUrsSettingsDao;
import me.cuiyijie.joyea.domain.JoyeaUrs;
import me.cuiyijie.joyea.domain.JoyeaUrsSettings;
import me.cuiyijie.joyea.service.IJoyeaUrsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JoyeaUrsServiceImpl implements IJoyeaUrsService {

    @Autowired
    private JoyeaUrsDao joyeaUrsDao;

    @Autowired
    private JoyeaUrsSettingsDao joyeaUrsSettingsDao;

    @Override
    public PageInfo<JoyeaUrs> selectByProjectNumber(String projectNumber, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<JoyeaUrs> result = joyeaUrsDao.selectByProjectNumber(projectNumber);

        for (int index = 0; index < result.size(); index++) {
            JoyeaUrs joyeaUrs = result.get(index);
            JoyeaUrsSettings joyeaUrsSettings = joyeaUrsSettingsDao.findById(joyeaUrs.getUrsId());
            if (joyeaUrsSettings == null) {
                joyeaUrs.setSettings(new JoyeaUrsSettings());
            } else {
                joyeaUrs.setSettings(joyeaUrsSettings);
            }
        }

        return new PageInfo<>(result);
    }

    @Override
    public Integer updateUrsSettings(JoyeaUrsSettings joyeaUrsSettings) {
        JoyeaUrsSettings exist = joyeaUrsSettingsDao.findById(joyeaUrsSettings.getUrsId());
        if (exist != null) {
            return joyeaUrsSettingsDao.update(joyeaUrsSettings);
        } else {
            return joyeaUrsSettingsDao.insert(joyeaUrsSettings);
        }
    }
}
