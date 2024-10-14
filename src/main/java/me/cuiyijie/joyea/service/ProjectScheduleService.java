package me.cuiyijie.joyea.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import me.cuiyijie.joyea.dao.ProjectScheduleDao;
import me.cuiyijie.joyea.model.ProjectSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author cyj976655@gmail.com
 * @date 2022/10/5 21:16
 */
@Service
@RequiredArgsConstructor
public class ProjectScheduleService {

    private final ProjectScheduleDao scheduleDao;

    public ProjectSchedule select(ProjectSchedule projectSchedule) {
        return scheduleDao.selectById(projectSchedule.getFid());
    }

}
