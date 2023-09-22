package com.blumbit.web.api.appgradle;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "First REST API, persona service", version = "1.0", description = "REST API for the Persona Application v1.0"))
public class AppGradleApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppGradleApplication.class, args);
	}

}
