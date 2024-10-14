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

    @Test
    void contextLoads() {

    }
}
