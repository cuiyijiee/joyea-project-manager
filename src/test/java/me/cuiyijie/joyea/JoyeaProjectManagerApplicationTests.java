package me.cuiyijie.joyea;

import me.cuiyijie.joyea.config.UserFileType;
import me.cuiyijie.joyea.dao.joyea.JoyeaAssemblyProblemDao;
import me.cuiyijie.joyea.dao.joyea.JoyeaPersonDao;
import me.cuiyijie.joyea.dao.joyea.JoyeaUrsDao;
import me.cuiyijie.joyea.dao.main.CheckItemFormDataDao;
import me.cuiyijie.joyea.dao.main.TestSheetDao;
import me.cuiyijie.joyea.domain.TestSheet;
import me.cuiyijie.joyea.service.ICheckItemFormSettingService;
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

    @Autowired
    JoyeaUrsDao joyeaUrsDao;

    @Autowired
    JoyeaAssemblyProblemDao joyeaAssemblyProblemDao;

    @Autowired
    TestSheetDao testSheetDao;

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

//        CheckItemFormData checkItemFormData = new CheckItemFormData();
//        checkItemFormData.setCheckItemId("1");
//        checkItemFormData.setRowIndex(1);
//        List<CheckItemFormData> checkItemFormDataList = checkItemFormDataDao.selectAllBy(checkItemFormData);
//        checkItemFormDataList.stream().forEach(item -> {
//            System.out.println(item);
//        });

//        List<JoyeaUrs> joyeaUrsList = joyeaUrsDao.selectByProjectNumber("");
//        System.out.println(joyeaUrsList);

//        List<JoyeaAssemblyProblem> problems = joyeaAssemblyProblemDao.selectByProjectNumber("J1707YCSW01S");
//        System.out.println(problems);

//        TestSheet testSheet = new TestSheet();
//        testSheet.setTestName("test");
//        testSheet.setTestType(UserFileType.QualityManual);
//        testSheetDao.insert(testSheet);

//        List<TestSheet> result = testSheetDao.selectById(1);
//        System.out.println(result);
    }

}
