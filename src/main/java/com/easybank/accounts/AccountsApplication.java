package com.easybank.accounts;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "AuditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Account Microservice Rest Api Documentation",
				description = "EasyBank Accounts microservices Rest API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Sunil Singh",
						email = "suniljavaboss@gmail.com",
						url = "https://www.sss.com"
				)

		)
)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
