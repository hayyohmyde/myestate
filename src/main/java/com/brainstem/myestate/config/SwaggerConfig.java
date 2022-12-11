package com.brainstem.myestate.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

//customizing Swagger details
@Configuration
public class SwaggerConfig {

    //define api instance
    private ApiInfo apiInfo(){
        return new ApiInfo(
                "MyEstate Properties Limited REST APIs",
                "MyEstate Properties Limited REST API Documentation",
                "1",
                "Terms Of Service",
                new Contact("Abdulrasheed Ilori", "https://www.myestate.com", "aaailori@gmail.com"),
                "License of API",
                "https://www.myestate.com/license",
                Collections.emptyList()
        );
    }
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
}
