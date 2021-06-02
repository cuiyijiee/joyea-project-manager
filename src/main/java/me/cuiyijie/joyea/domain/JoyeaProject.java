package me.cuiyijie.joyea.domain;


public class JoyeaProject {

    /**
     * 生产单号
     */
    private String fNumber;

    /**
     *销售编号
     */
    private String fRelatingOrderNum;

    /**
     *销售序号
     */
    private String fRelatingEntrySeq;

    private String fMaterialID;

    private String fNameL2;

    /**
     * 名称
     */
    private String fNumber2;

    private String cFProjectName;

    private String fNumber3;

    private String cFTaskName;

    public String getfNumber() {
        return fNumber;
    }

    public void setfNumber(String fNumber) {
        this.fNumber = fNumber;
    }

    public String getfRelatingOrderNum() {
        return fRelatingOrderNum;
    }

    public void setfRelatingOrderNum(String fRelatingOrderNum) {
        this.fRelatingOrderNum = fRelatingOrderNum;
    }

    public String getfRelatingEntrySeq() {
        return fRelatingEntrySeq;
    }

    public void setfRelatingEntrySeq(String fRelatingEntrySeq) {
        this.fRelatingEntrySeq = fRelatingEntrySeq;
    }

    public String getfMaterialID() {
        return fMaterialID;
    }

    public void setfMaterialID(String fMaterialID) {
        this.fMaterialID = fMaterialID;
    }

    public String getfNameL2() {
        return fNameL2;
    }

    public void setfNameL2(String fNameL2) {
        this.fNameL2 = fNameL2;
    }

    public String getfNumber2() {
        return fNumber2;
    }

    public void setfNumber2(String fNumber2) {
        this.fNumber2 = fNumber2;
    }

    public String getcFProjectName() {
        return cFProjectName;
    }

    public void setcFProjectName(String cFProjectName) {
        this.cFProjectName = cFProjectName;
    }

    public String getfNumber3() {
        return fNumber3;
    }

    public void setfNumber3(String fNumber3) {
        this.fNumber3 = fNumber3;
    }

    public String getcFTaskName() {
        return cFTaskName;
    }

    public void setcFTaskName(String cFTaskName) {
        this.cFTaskName = cFTaskName;
    }
}
