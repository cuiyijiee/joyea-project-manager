package me.cuiyijie.joyea;

import me.cuiyijie.joyea.config.UserFileType;
import me.cuiyijie.joyea.dao.joyea.JoyeaAssemblyProblemDao;
import me.cuiyijie.joyea.dao.joyea.JoyeaPersonDao;
import me.cuiyijie.joyea.dao.joyea.JoyeaUrsDao;
import me.cuiyijie.joyea.dao.main.CheckItemFormDataDao;
import me.cuiyijie.joyea.dao.main.SheetColumnDao;
import me.cuiyijie.joyea.dao.main.TestSheetDao;
import me.cuiyijie.joyea.dao.main.UserDao;
import me.cuiyijie.joyea.domain.TestSheet;
import me.cuiyijie.joyea.model.NextPlusTenantMember;
import me.cuiyijie.joyea.model.SheetColumn;
import me.cuiyijie.joyea.model.User;
import me.cuiyijie.joyea.pojo.NextPlusTenant;
import me.cuiyijie.joyea.service.ICheckItemFormSettingService;
import me.cuiyijie.joyea.service.INextPlusService;
import me.cuiyijie.joyea.util.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
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
    UserDao userDao;

    @Autowired
    JoyeaAssemblyProblemDao joyeaAssemblyProblemDao;

    @Autowired
    TestSheetDao testSheetDao;

    @Autowired
    SheetColumnDao sheetColumnDao;

    @Autowired
    INextPlusService nextPlusService;

    @Test
    void contextLoads() {

        nextPlusService.sendNotify(Arrays.asList("8a9c8ff35deb01ad015dee4c08920019"),"测试信息");
//        NextPlusTenant tenant = nextPlusService.getTenantInfo();
//        System.out.println(tenant);
//
//        for (int index = 0; index < tenant.getMembers().size(); index++) {
//            NextPlusTenantMember member = tenant.getMembers().get(index);
//            User existUser = userDao.listById(member.getId());
//            if(existUser == null) {
//                User user = new User();
//                BeanUtils.copyProperties(member,user);
//                userDao.insert(user);
//            }
//        }

//        List<SheetColumn> columns = sheetColumnDao.findAll(new SheetColumn());
//        System.out.println(columns);

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
