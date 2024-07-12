package com.test.iasapi;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "IAS API Documentation",
				description = "John Loans microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "ME",
						email = "juan.ardila@ias.com.co"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "IAS Loans microservice REST API Documentation"
		)
)
public class IasapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(IasapiApplication.class, args);
	}

}
