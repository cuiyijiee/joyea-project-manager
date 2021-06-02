package me.cuiyijie.joyea.domain;

import com.fasterxml.jackson.annotation.JsonAlias;

public class JoyeaUserProfile {

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

    public int getSubCompanyId() {
        return subCompanyId;
    }

    public void setSubCompanyId(int subCompanyId) {
        this.subCompanyId = subCompanyId;
    }

    public String getWorkCode() {
        return workCode;
    }

    public void setWorkCode(String workCode) {
        this.workCode = workCode;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSystemLanguage() {
        return systemLanguage;
    }

    public void setSystemLanguage(String systemLanguage) {
        this.systemLanguage = systemLanguage;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
