package br.com.gntech.gestaovagas.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition
@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI openAPI() {
    return new OpenAPI()
      .info(new Info().title("Gestão de vagas").description("Api responsavel pelo gestao de vagas").version("1.0.0"))
      .schemaRequirement("jwt_auth", securityScheme());
  }

  private SecurityScheme securityScheme() {
    return new SecurityScheme()
      .name("jwt_auth")
      .scheme("bearer")
      .type(SecurityScheme.Type.HTTP)
      .in(SecurityScheme.In.HEADER);
  }
}
