package com.website.online.sale.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.info.Info;


@Configuration
@OpenAPIDefinition(
        security = @SecurityRequirement(name = "Bearer Authentication"),
        servers = {
                @Server(url = "http://127.0.0.1:8080/", description = "Local"),
                @Server(url = "https://employee-service-api.ghtklab.com/", description = "Testing"),
                @Server(url = "https://employee-service-api.ghtk.vn/", description = "Production")
        }
)
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)

public class SwaggerConfig {
    @Bean
    public OpenAPI usersMicroserviceOpenAPI() {
        return new OpenAPI()
                .info(new Info().
                        title("Employee Service Api")
                        .version("1.0"))
                ;
    }
}

