package me.cuiyijie.joyea.auth.config;

import me.cuiyijie.joyea.enums.base.EnumConvertFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Autowired
    private EnumConvertFactory enumConvertFactory;

    @Bean
    public JwtAuthenticationInterceptor setBean() {
        return new JwtAuthenticationInterceptor();
    }

    @Bean
    public CurrentUserMethodArgumentResolver currentUserMethodArgumentResolver() {
        return new CurrentUserMethodArgumentResolver();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(setBean()).addPathPatterns("/**")
                .excludePathPatterns("/nextplus/**","/swagger-ui/**","/swagger-resources/**","/webjars/**","/v2/**","/favicon.ico");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(currentUserMethodArgumentResolver());
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(enumConvertFactory);
    }
}
