package org.cnr.fo3xdb.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// http://localhost:8080/swagger-ui/index.html


@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI defineOpenApi() {

        return new OpenAPI()
                .info(new Info()
                        .title("FO3X (Ozone FACE – free air controlled exposure)")
                        .version("1.0.0")
                        .license(new License()
                                .name("MIT License")
                                .url("https://choosealicense.com/licenses/mit/")
                        )
                        .description("""
                        This API exposes endpoints to manage the FO3X database.
                        ### Authors
                        - **Elena Marra** - Institute of Research on Terrestrial Ecosystems (IRET) of the National Research Council (CNR), Italy
                        - **Alessandro Montaghi** - Institute of Research on Terrestrial Ecosystems (IRET) of the National Research Council (CNR), Italy
                        - **Elena Paoletti** -  Institute of Research on Terrestrial Ecosystems (IRET) of the National Research Council (CNR), Italy
                         """)
                        .version("1.0")
                        .contact(new Contact()
                                .name("Alessandro Montaghi")
                                .email("alessandro.montaghi@cnr.it")
                                .url("https://www.cnr.it"))
                );
    }
}
