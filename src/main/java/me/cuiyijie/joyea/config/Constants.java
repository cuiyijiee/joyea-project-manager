package me.cuiyijie.joyea.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

//常量
@Component
public class Constants implements EnvironmentAware {

    private static Environment environment;

    public final static String NEXT_PLUS_ACCESS_TOKEN_URL = "https://open.nextxx.cn/openapi/oauth/token";
    public final static String NEXT_PLUS_TICKET_URL = "https://open.nextxx.cn/openapi/oauth/ticket";
    public final static String NEXT_PLUS_PROFILE_URL = "http://hr.joyea.cn:8099/openapi/find-ytm-user-by-code/";
    public final static String NEXT_PLUS_JOYEA_TENANT_MEMBER_URL = "https://open.nextxx.cn/openapi/group/members/8a9c8ff35deb9042015dee0ea0bf0011?tenantId=3347731b-5c0e-4a80-9dff-6c66aa79fd8c";
    public final static String NEXT_PLUS_SEND_NOTIFICATION_URL = "https://open.nextxx.cn/openapi/message/send-to-eapp";
    public final static String NEXT_PLUS_DEPARTMENT_URL = "http://hr.joyea.cn:8099/openapi/find-eas-department-list";

    public static String getNextPlusClientId(){
        return getProperty("joyea.oauth2.nextplus.clientId");
    }

    public static String getNextPlusClientSecret(){
        return getProperty("joyea.oauth2.nextplus.clientSecret");
    }

    //成功code
    public static final String SUCCESS_CODE = "200";

    //用户身份信息认真过期
    public static final String USER_AUTHENTICATION_EXPIRED_CODE = "401";

    //上传文件为空
    public static final String UPLOAD_FILE_EMPTY_CODE = "5001";


    public static final String UNKNOWN_ERROR_CODE = "1001";

    @Override
    public void setEnvironment(Environment environment) {
        injectEnvironment(environment);
    }

    public static void injectEnvironment(Environment environment){
        Constants.environment = environment;
    }

    public static String getProperty(String key){
        return environment.getProperty(key);
    }
}
