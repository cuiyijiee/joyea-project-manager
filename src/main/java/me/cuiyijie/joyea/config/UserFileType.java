package me.cuiyijie.joyea.config;

/**
 * @Author:
 * @Date: 2021/8/30 14:48
 */
public enum UserFileType {

    QualityManual("1001"), //质量手册
    SecurityManual("1002"), //安全手册
    Urs("1003");  //非标URS

    private final String value;

    private UserFileType(String value) {
        this.value = value;
    }

    public String toString() {
        return this.value;
    }

    public static UserFileType get(int value) {
        String str = String.valueOf(value);
        return get(str);
    }

    public static UserFileType get(String str) {
        for (UserFileType type : values()) {
            if (type.toString().equals(str)) {
                return type;
            }
        }
        return null;
    }
}
