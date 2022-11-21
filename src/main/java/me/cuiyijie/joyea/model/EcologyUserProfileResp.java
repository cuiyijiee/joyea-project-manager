package me.cuiyijie.joyea.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public class EcologyUserProfileResp {


    private String msg;
    private String code;
    private int status;
    private String id;

    private JoyeaUserProfileDTO attributes;

    @Data
    public static class JoyeaUserProfileDTO {
        @JsonAlias("subcompanyid")
        private int subCompanyId;

        @JsonAlias("workcode")
        private String workCode;

        private String sex;

        @JsonAlias("departmentid")
        private int departmentId;

        private String mobile;


        @JsonAlias("systemlanguage")
        private String systemLanguage;

        private String telephone;

        @JsonAlias("managerid")
        private String managerId;

        @JsonAlias("lastname")
        private String lastName;

        private int id;

        @JsonAlias("countryid")
        private int countryId;

        private String email;

        private String status;
    }
}


