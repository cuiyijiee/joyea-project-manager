package me.cuiyijie.joyea;

import me.cuiyijie.joyea.domain.JoyeaAccessToken;
import me.cuiyijie.joyea.domain.JoyeaUserProfile;
import me.cuiyijie.joyea.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JoyeaProjectManagerApplicationTests {

    @Autowired
    UserService userService;

    @Test
    void contextLoads() {
//        JoyeaAccessToken result = userService.getAccessTokenByTicket("ST-891-eHFF6mckJVZZ9bUaDz4r-c01");
//        JoyeaUserProfile userInfo = userService.getUserInfoByToken(result.getAccessToken());
//        System.out.println(userInfo);
    }

}