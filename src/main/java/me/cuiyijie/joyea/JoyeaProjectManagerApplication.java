package me.cuiyijie.joyea;

import com.github.pagehelper.autoconfigure.PageHelperAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication(exclude = PageHelperAutoConfiguration.class)
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class JoyeaProjectManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(JoyeaProjectManagerApplication.class, args);
    }

}
