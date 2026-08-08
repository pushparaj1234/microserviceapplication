package com.push.accounts;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Accounts microservices REST API Documentation",
				description = "Bank accounts microservices REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Pushparaj",
						email = "pushparajjekki@gmail.com"
				),
				license = @License(
						name = "Apache 2.O"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Bank accounts microservices REST API Documentation",
				url = "http://localhost:8080/swagger-ui.html"
		)
)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
