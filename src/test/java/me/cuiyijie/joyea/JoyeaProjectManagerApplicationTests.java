package me.cuiyijie.joyea;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import me.cuiyijie.joyea.dao.CheckItemAttachmentDao;
import me.cuiyijie.joyea.dao.ProjectDao;
import me.cuiyijie.joyea.model.CheckItemAttachment;
import me.cuiyijie.joyea.model.Project;
import me.cuiyijie.joyea.service.CheckItemAttachmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
class JoyeaProjectManagerApplicationTests {


    @Autowired
    private ProjectDao newProjectDao;

    @Autowired
    private CheckItemAttachmentService checkItemAttachmentService;

    @Autowired
    private CheckItemAttachmentDao checkItemAttachmentDao;

    @Test
    void contextLoads() {
//        List<Project> projectList = newProjectDao.selectList(new QueryWrapper<>());
//        System.out.println(projectList);

//        checkItemAttachmentService.login();
//        checkItemAttachmentService.getAttachment("NMUAAAOcGK70r08D");

//        Page<CheckItemAttachment> attachmentPage = checkItemAttachmentDao.selectPage(new Page<>(0,10),new QueryWrapper<>());
//        log.info("attachment page: " + attachmentPage);

        log.info("real file name: " + checkItemAttachmentService.getFileName("/mnt/sdb1/eas/EAS/2AA228EC/20160215/20160217140719953_20160217140603106.xlsx"));
    }

}
