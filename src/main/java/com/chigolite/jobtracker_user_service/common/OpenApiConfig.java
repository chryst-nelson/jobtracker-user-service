package com.chigolite.jobtracker_user_service.common;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

        @Bean
        public io.swagger.v3.oas.models.OpenAPI openAPI() {
                return new io.swagger.v3.oas.models.OpenAPI()
                                .info(new Info()
                                                .title("JobTracker User Service API")
                                                .description("Handles authentication and user management")
                                                .version("v1.0"))
                                .addSecurityItem(new SecurityRequirement()
                                                .addList("Bearer Authentication"))
                                .components(new Components()
                                                .addSecuritySchemes("Bearer Authentication",
                                                                new SecurityScheme()
                                                                                .type(SecurityScheme.Type.HTTP)
                                                                                .scheme("bearer")
                                                                                .bearerFormat("JWT")));
        }
}