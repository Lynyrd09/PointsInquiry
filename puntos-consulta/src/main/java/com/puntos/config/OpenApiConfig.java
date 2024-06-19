package com.puntos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {
    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI().info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title("Orquestador Puntos API")
                .description("Consulta Servicio de Puntos")
                .version("2.0")
                //.contact(apiContact())
                .license(apiLicence());
    }

    private License apiLicence() {
        return new License()
                .name("MIT Licence")
                .url("https://opensource.org/licenses/mit-license.php");
    }
    /**
    private Contact apiContact() {
        return new Contact()
                .name("Erwan LE TUTOUR")
                .email("erwanletutour.elt@gmail.com")
                .url("https://github.com/ErwanLT");
    }
    */
}
