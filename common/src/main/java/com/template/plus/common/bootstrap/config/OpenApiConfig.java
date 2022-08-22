package com.template.plus.common.bootstrap.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lp
 * @date 2022/5/18 17:04
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("TruckApi API")
                        .description("Spring TruckApi application")
                        .version("v1.0")
                )
                .externalDocs(new ExternalDocumentation()
                        .description("TruckApi Documentation"));
    }
}
