package me.cuiyijie.joyea.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import me.cuiyijie.joyea.dao.ProjectScheduleDao;
import me.cuiyijie.joyea.model.ProjectSchedule;
import org.springframework.stereotype.Service;

/**
 * @author cyj976655@gmail.com
 * @date 2022/10/5 21:16
 */
@Service
@RequiredArgsConstructor
public class ProjectScheduleService extends ServiceImpl<ProjectScheduleDao, ProjectSchedule> {


    public ProjectSchedule select(ProjectSchedule projectSchedule) {
        return baseMapper.selectById(projectSchedule.getFid());
    }

}
