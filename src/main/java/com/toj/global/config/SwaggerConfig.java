package com.toj.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    /**
     * swagger 주소 : http://localhost:8080/swagger-ui/index.html#
     */
    @Bean
    public OpenAPI openAPI() {
        String key = "Authorization";

        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement()
                        .addList(key)
                )
                .info(apiInfo())
                .components(new Components()
                        .addSecuritySchemes(key, new SecurityScheme()
                                .name(key)
                                .type(SecurityScheme.Type.HTTP)
                                .in(SecurityScheme.In.HEADER)
                                .scheme("bearer")
                                .bearerFormat("JWT"))
                );
    }

    private Info apiInfo() {
        return new Info()
                .title("Springdoc")
                .description("To J API Swagger UI")
                .version("1.0.0");
    }


}


