package me.cuiyijie.joyea.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author cyj976655@gmail.com
 * @date 2021/6/30 23:07
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final String SWAGGER_SCAN_BASE_PACKAGE = "me.cuiyijie.joyea.controller";
    public static final String VERSION = "1.0.0";

    @Configuration
    public class SpringFoxConfig {
        @Bean
        public Docket api() {
            return new Docket(DocumentationType.OAS_30)
                    .apiInfo(apiInfo())
                    .select()
                    .apis(RequestHandlerSelectors.basePackage(SWAGGER_SCAN_BASE_PACKAGE))
                    .paths(PathSelectors.any())
                    .build();
        }
    }


    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("仅一质量管理后端API")
                .description("仅一质量管理后端API文档")
                .license("Apache 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0.html")
                .termsOfServiceUrl("")
                .version(VERSION)
                .contact(new Contact("cuiyijie", "https://cuje.tk", "cyj976655@gmail.com"))
                .build();
    }

}
