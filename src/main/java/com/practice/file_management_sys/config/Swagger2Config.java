package com.practice.file_management_sys.config;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/***
 * @Author Saul
 * @Description  TODO: 项目接口管理swagger配置
 * @Date 11:18 下午 28/2/20
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
    private static final Logger LOGGER = LoggerFactory.getLogger(Swagger2Config.class);

    @Bean
    public Docket swaggerDocket(){
        LOGGER.debug("FMS--swagger配置API基本信息");
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder().description("FMS 系统接口文档")
                .contact(new Contact("Saul", "http://localhost:8080/index.html", "shuaimou77@gmail.com"))
                .version("1.0.0")
                .termsOfServiceUrl("http://47.74.88.139:8080/")
                .build();
    }
}
