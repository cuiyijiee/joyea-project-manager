package me.cuiyijie.joyea.enums.base;

public class CodeEnumUtil {

    public static <E extends Enum<?> & BaseEnum> E codeOf(Class<E> enumClass, String code) {
        E[] enumConstants = enumClass.getEnumConstants();
        for (E e : enumConstants) {
            if (e.getKey().equals(code))
                return e;
        }
        return null;
    }

}
