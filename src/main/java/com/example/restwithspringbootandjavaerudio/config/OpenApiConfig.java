package com.example.restwithspringbootandjavaerudio.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI().info(new Info()
                .title("RestFull Api with java 19 and Spring boot 3.1.2")
                .version("v1")
                .description("This is a description about a API")
                //link de termos  de serviço
                .termsOfService("https://github.com/Martins98725/rest-with-springboot-and-java-erudio.git")
                .license(new License()
                        .name("Apache 2.0")
                        //link de licença
                        .url("https://github.com/Martins98725/rest-with-springboot-and-java-erudio.git")));
    }
}
