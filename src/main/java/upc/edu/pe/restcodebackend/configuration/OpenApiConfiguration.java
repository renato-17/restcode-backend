package upc.edu.pe.restcodebackend.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {
    @Bean(name = "restCodeOpenApi")
    public OpenAPI restCodeOpenApi() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("RestCode Application API")
                        .description(
                                "RestCode API implemented with Spring Boot RESTful service and documented using springdoc-openapi and OpenAPI 3.0"));

    }

}
