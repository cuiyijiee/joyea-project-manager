package me.cuiyijie.joyea.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.toolkit.MPJWrappers;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.github.yulichang.wrapper.enums.BaseFuncEnum;
import lombok.extern.slf4j.Slf4j;
import me.cuiyijie.joyea.dao.ProjectCollectDao;
import me.cuiyijie.joyea.dao.ProjectDao;
import me.cuiyijie.joyea.exception.SysRuntimeException;
import me.cuiyijie.joyea.model.CheckItemResult;
import me.cuiyijie.joyea.model.Person;
import me.cuiyijie.joyea.model.Project;
import me.cuiyijie.joyea.model.ProjectCollect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.function.Consumer;

@Slf4j
@Service
public class ProjectService {

    @Autowired
    private ProjectDao projectDao;

    @Autowired
    private ProjectCollectDao projectCollectDao;

    public Page<Project> list(String easUserId, Project project, Integer pageNum, Integer pageSize) {
        Page<Project> projectPage = new Page<>(pageNum, pageSize);

//        IPage<Project> projectIPage = projectDao.selectJoinPage(projectPage, Project.class,
//                MPJWrappers.lambdaJoin()
//                        .selectAll(Project.class)
//                        .leftJoin(ProjectCollect.class, ProjectCollect::getProjectId, Project::getFid)
//                        .eq(Project::getFid, project.getFid())
//                        .orderByDesc(CheckItemResult::getCfCheckDate)
//        );

        QueryWrapper<Project> queryWrapper = new QueryWrapper<>();
        if (project.getProjectName() != null && project.getProjectName().length() > 0) {
            queryWrapper = queryWrapper.like("PROJECTNAME", project.getProjectName())
                    .or()
                    .like("FNUMBER", project.getProjectName());
        }

        Page<Project> resultProjectPage = projectDao.selectPage(projectPage, queryWrapper);

        for (Project record : resultProjectPage.getRecords()) {
            ProjectCollect existProjectCollect = projectCollectDao.selectOne(new QueryWrapper<ProjectCollect>()
                    .eq("EASUSERID", easUserId)
                    .eq("PROJECTID", record.getFid())
            );
            record.setCollect(existProjectCollect != null);
        }
        return resultProjectPage;
    }

    public Page<Project> listCollect(String easUserId, Project project, Integer pageNum, Integer pageSize) {
        Page<Project> projectPage = new Page<>(pageNum, pageSize);
        MPJLambdaWrapper<Project> wrapper = new MPJLambdaWrapper<Project>()
                .selectAll(Project.class)
                .leftJoin(ProjectCollect.class, ProjectCollect::getProjectId, Project::getFid)
                .eq(ProjectCollect::getEasUserId, easUserId)
                .and(StringUtils.hasLength(project.getProjectName()), objectMPJLambdaWrapper -> objectMPJLambdaWrapper.like(Project::getProjectName, project.getProjectName())
                        .or()
                        .like(Project::getFNumber, project.getProjectName()));

        Page<Project> resultProjectPage = projectDao.selectPage(projectPage, wrapper);
        for (Project record : resultProjectPage.getRecords()) {
            record.setCollect(true);
        }
        return resultProjectPage;
    }

    public void addCollectProject(String userId, String projectId) {
        ProjectCollect existProjectCollect = projectCollectDao.selectOne(new QueryWrapper<ProjectCollect>()
                .eq("EASUSERID", userId)
                .eq("PROJECTID", projectId)
        );

        if (existProjectCollect == null) {
            ProjectCollect newProjectCollect = new ProjectCollect();
            newProjectCollect.setProjectId(projectId);
            newProjectCollect.setEasUserId(userId);
            newProjectCollect.setUpdateTime(new Date());
            projectCollectDao.insert(newProjectCollect);
        } else {
            projectCollectDao.deleteById(existProjectCollect.getFid());
        }
    }

    public Integer insert(Project project) {
        return null;
    }

    public Integer update(Project project) {
        return null;
    }

}
