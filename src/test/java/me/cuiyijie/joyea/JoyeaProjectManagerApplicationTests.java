package me.cuiyijie.joyea;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import me.cuiyijie.joyea.dao.CheckItemAttachmentDao;
import me.cuiyijie.joyea.dao.ProjectDao;
import me.cuiyijie.joyea.model.CheckItemAttachment;
import me.cuiyijie.joyea.model.Project;
import me.cuiyijie.joyea.model.SearchHistory;
import me.cuiyijie.joyea.pojo.FilezUploadFileRegionResp;
import me.cuiyijie.joyea.service.CheckItemAttachmentService;
import me.cuiyijie.joyea.service.FilezService;
import me.cuiyijie.joyea.service.SearchHistoryService;
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

    @Autowired
    private FilezService filezService;

    @Autowired
    private SearchHistoryService searchHistoryService;

    @Test
    void contextLoads() {
//        List<Project> projectList = newProjectDao.selectList(new QueryWrapper<>());
//        System.out.println(projectList);

//        checkItemAttachmentService.login();
//        checkItemAttachmentService.getAttachment("NMUAAAOcGK70r08D");

//        Page<CheckItemAttachment> attachmentPage = checkItemAttachmentDao.selectPage(new Page<>(0,10),new QueryWrapper<>());
//        log.info("attachment page: " + attachmentPage);

//        log.info("real file name: " + checkItemAttachmentService.getFileName("/mnt/sdb1/eas/EAS/2AA228EC/20160215/20160217140719953_20160217140603106.xlsx"));
//        FilezUploadFileRegionResp region = filezService.getUploadFileRegion("测试文件");
//        log.info("get upload file region: " + region);
//        for (int index = 0; index <10; index++) {
//            searchHistoryService.addSearchHistory("ceshi", "Test", "search-key-" + index);
//        }

        List<SearchHistory> searchHistories = searchHistoryService.getLastHistory("ceshi","Test",10);
        System.out.println(searchHistories);
    }

}
