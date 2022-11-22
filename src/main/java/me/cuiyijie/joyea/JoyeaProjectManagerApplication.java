package me.cuiyijie.joyea;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("me.cuiyijie.joyea.dao")
@SpringBootApplication()
public class JoyeaProjectManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(JoyeaProjectManagerApplication.class, args);
    }

}
