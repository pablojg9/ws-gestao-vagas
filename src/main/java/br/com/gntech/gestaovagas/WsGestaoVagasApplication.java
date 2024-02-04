package br.com.gntech.gestaovagas;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
    info = @Info(
            title = "Gestão de vagas",
            description = "Api responsavel pelo gestao de vagas",
            version = "1.0.0"
    )
)
@SecurityScheme(name = "jwt_auth",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        in = SecuritySchemeIn.HEADER
)
public class WsGestaoVagasApplication {

    public static void main(String[] args) {
        SpringApplication.run(WsGestaoVagasApplication.class, args);
    }
}
