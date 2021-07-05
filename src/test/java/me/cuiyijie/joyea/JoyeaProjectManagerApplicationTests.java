package me.cuiyijie.joyea;

import me.cuiyijie.joyea.dao.joyea.JoyeaPersonDao;
import me.cuiyijie.joyea.dao.main.CheckItemFormDataDao;
import me.cuiyijie.joyea.domain.CheckItemFormData;
import me.cuiyijie.joyea.domain.CheckItemFormSetting;
import me.cuiyijie.joyea.domain.JoyeaPerson;
import me.cuiyijie.joyea.service.ICheckItemFormSettingService;
import me.cuiyijie.joyea.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class JoyeaProjectManagerApplicationTests {

    @Autowired
    JoyeaPersonDao joyeaPersonDao;

    @Autowired
    ICheckItemFormSettingService checkItemFormSettingService;

    @Autowired
    CheckItemFormDataDao checkItemFormDataDao;

    @Test
    void contextLoads() {
//        JoyeaAccessToken result = userService.getAccessTokenByTicket("ST-891-eHFF6mckJVZZ9bUaDz4r-c01");
//        JoyeaUserProfile userInfo = userService.getUserInfoByToken(result.getAccessToken());
//        System.out.println(userInfo);

//        JoyeaPerson joyeaPerson = new JoyeaPerson();
//        joyeaPerson.setName("徐");
//        List<JoyeaPerson> persons = joyeaPersonDao.list(joyeaPerson);
//        persons.stream().forEach(System.out::println);

//        List<CheckItemFormSetting> checkItemFormSettingList = checkItemFormSettingService.listAll("123");
//
//        checkItemFormSettingList.stream().forEach(item -> {
//            System.out.println(item);
//        });

        CheckItemFormData checkItemFormData = new CheckItemFormData();
        checkItemFormData.setCheckItemId("1");
        checkItemFormData.setRowIndex(1);
        List<CheckItemFormData> checkItemFormDataList = checkItemFormDataDao.selectBy(checkItemFormData);
        checkItemFormDataList.stream().forEach(item -> {
            System.out.println(item);
        });
    }

}
