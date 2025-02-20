package bj.formation.demoprojet.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI documentation() {
        OpenAPI api = new OpenAPI();
        Info info = new Info();
               info.title("Gestion des ressources humaines")
                .description("Formation SpringBoot")
                .version("1.2")
                       .license(new License().name("Apache 2.0").url("http://springdoc.org"));
        return api.info(info);
    }
}
