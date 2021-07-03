package me.cuiyijie.joyea.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class FileUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);

    //根据文件名获取文件后缀,不带句号
    public static String getFileSuffixWithoutPeriod(String fileName) {
        return Optional.ofNullable(fileName)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(f.lastIndexOf(".") + 1)).orElse("");
    }
}
