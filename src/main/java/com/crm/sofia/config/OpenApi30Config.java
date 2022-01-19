package com.crm.sofia.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;


@Configuration
@OpenAPIDefinition(info = @io.swagger.v3.oas.annotations.info.Info(title = "My API", version = "v3"))
@io.swagger.v3.oas.annotations.security.SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class OpenApi30Config {
}

//@Configuration
//public class OpenApi30Config {
//
//
//
//    public OpenApi30Config() {
//    }
//
//    @Bean
//    public OpenAPI customOpenAPI() {
//        final String securitySchemeName = "bearerAuth";
//        final String apiTitle = String.format("Rita RTM API");
//
//        return new OpenAPI()
//                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
//                .components(
//                        new Components()
//                                .addSecuritySchemes(securitySchemeName,
//                                        new SecurityScheme()
//                                                .name(securitySchemeName)
//                                                .type(SecurityScheme.Type.HTTP)
//                                                .scheme("bearer")
//                                                .bearerFormat("JWT")
//                                )
//                );
//               // .info(new Info().title(apiTitle).version("v3"));
//    }
//}
