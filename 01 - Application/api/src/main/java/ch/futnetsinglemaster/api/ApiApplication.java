package ch.futnetsinglemaster.api;

import ch.futnetsinglemaster.api.security.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "FutNet Single Master API", version = "1.0", description = "created by @paschek"))
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

}
