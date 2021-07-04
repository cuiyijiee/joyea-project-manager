package me.cuiyijie.joyea;

import com.github.pagehelper.autoconfigure.PageHelperAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = PageHelperAutoConfiguration.class)
public class JoyeaProjectManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(JoyeaProjectManagerApplication.class, args);
    }

}
