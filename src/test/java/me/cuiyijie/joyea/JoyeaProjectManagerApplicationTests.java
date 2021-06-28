package me.cuiyijie.joyea;

import me.cuiyijie.joyea.dao.joyea.JoyeaPersonDao;
import me.cuiyijie.joyea.domain.JoyeaPerson;
import me.cuiyijie.joyea.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class JoyeaProjectManagerApplicationTests {

    @Autowired
    JoyeaPersonDao joyeaPersonDao;

    @Test
    void contextLoads() {
//        JoyeaAccessToken result = userService.getAccessTokenByTicket("ST-891-eHFF6mckJVZZ9bUaDz4r-c01");
//        JoyeaUserProfile userInfo = userService.getUserInfoByToken(result.getAccessToken());
//        System.out.println(userInfo);

        JoyeaPerson joyeaPerson = new JoyeaPerson();
        joyeaPerson.setName("徐");
        List<JoyeaPerson> persons = joyeaPersonDao.list(joyeaPerson);
        persons.stream().forEach(System.out::println);
    }

}
