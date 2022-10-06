package me.cuiyijie.joyea;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import me.cuiyijie.joyea.dao.ProjectDao;
import me.cuiyijie.joyea.model.Project;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class JoyeaProjectManagerApplicationTests {


    @Autowired
    private ProjectDao newProjectDao;


    @Test
    void contextLoads() {
        List<Project> projectList = newProjectDao.selectList(new QueryWrapper<>());
        System.out.println(projectList);
    }

}
