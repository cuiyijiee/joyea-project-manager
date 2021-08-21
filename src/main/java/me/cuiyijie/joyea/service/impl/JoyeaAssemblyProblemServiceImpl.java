package me.cuiyijie.joyea.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import me.cuiyijie.joyea.dao.joyea.JoyeaAssemblyProblemDao;
import me.cuiyijie.joyea.dao.main.JoyeaAssemblyProblemSettingsDao;
import me.cuiyijie.joyea.domain.JoyeaAssemblyProblem;
import me.cuiyijie.joyea.domain.JoyeaAssemblyProblemSettings;
import me.cuiyijie.joyea.service.IJoyeaAssemblyProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JoyeaAssemblyProblemServiceImpl implements IJoyeaAssemblyProblemService {

    @Autowired
    private JoyeaAssemblyProblemDao joyeaAssemblyProblemDao;

    @Autowired
    private JoyeaAssemblyProblemSettingsDao joyeaAssemblyProblemSettingsDao;

    @Override
    public PageInfo<JoyeaAssemblyProblem> selectByProjectNumber(String projectNumber, Integer pageSize, Integer pageNum) {

        PageHelper.startPage(pageNum, pageSize);
        List<JoyeaAssemblyProblem> result = joyeaAssemblyProblemDao.selectByProjectNumber(projectNumber);

        for (int index = 0; index < result.size(); index++) {
            JoyeaAssemblyProblem joyeaAssemblyProblem = result.get(index);
            JoyeaAssemblyProblemSettings joyeaAssemblyProblemSettings = joyeaAssemblyProblemSettingsDao.findById(joyeaAssemblyProblem.getProblemId());
            if (joyeaAssemblyProblemSettings == null) {
                joyeaAssemblyProblem.setSettings(new JoyeaAssemblyProblemSettings());
            } else {
                joyeaAssemblyProblem.setSettings(joyeaAssemblyProblemSettings);
            }
        }

        return new PageInfo<>(result);

    }

    @Override
    public Integer updateProblemSettings(JoyeaAssemblyProblemSettings joyeaAssemblyProblemSettings) {

        JoyeaAssemblyProblemSettings exist = joyeaAssemblyProblemSettingsDao.findById(joyeaAssemblyProblemSettings.getProblemId());
        if (exist != null) {
            return joyeaAssemblyProblemSettingsDao.update(joyeaAssemblyProblemSettings);
        } else {
            return joyeaAssemblyProblemSettingsDao.insert(joyeaAssemblyProblemSettings);
        }

    }
}
