package com.example.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                //.apis(RequestHandlerSelectors.basePackage("com.fixelsoft.assignment")) //스캔 범위 설정
                .apis(RequestHandlerSelectors.basePackage("com.assign")) //스캔 범위 설정
                //.apis(RequestHandlerSelectors.any()) 스웨거가 RestController를 전부 스캔
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("회원 API")
                .description("회원 API 입니다")
                .version("0.8.0")
                //.license("LICENSE")
                .licenseUrl("")
                .build();
    }

    // http://localhost:8080/swagger-ui.html
}
