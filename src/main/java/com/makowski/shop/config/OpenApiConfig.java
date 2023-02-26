package com.makowski.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {
    
    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
            .info(new Info()
            .title("Online Shop Backend")
            .description("An REST API to manage online shop with MySQL Database, secure with JSON Web Token and with User e-mail verification (for test only with Role : EMPLOYEE).Postman request collection : https://github.com/Michal-Makowski/Online-shop-backend/blob/main/Shop.postman_collection.json")
            .version("v1.0"));
    }
}
