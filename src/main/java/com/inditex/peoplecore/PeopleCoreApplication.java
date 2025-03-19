package com.inditex.peoplecore;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PeopleCoreApplication {

  public static void main(String[] args) {
    SpringApplication.run(PeopleCoreApplication.class, args);
  }

  @Bean
  public OpenAPI peopleCoreOpenAPI() {
    return new OpenAPI()
        .components(new Components())
        .info(new Info()
            .title("People Core API")
            .version("0.0.1-SNAPSHOT"));
  }
}
