package org.serratec.grupo1.projetofinal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {
    
    @Bean
	OpenAPI customOpenAPI() {
		return new OpenAPI()
			.info(new Info()
				.title("RESTful API - Grupo 1")
				.version("v1.0.2")
				.description("Api pra projetos e tarefas")
				.termsOfService("https://grupo1.com.br")
				.license(
					new License()
						.name("Apache 2.0")
						.url("https://org.serratec.com")
					)
				);
	}

}
