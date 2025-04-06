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
                        This API exposes endpoints to manage the FO3X database. FO3X O3-FACE (Free-air O3 eXposure) is
                        a multidisciplinary study to assess the effects of increasing tropospheric ozone and other
                        stress factors on vegetation under open-air conditions. FO3X is an ecosystem-level manipulative 
                        research facility that ensures long-term operability, fully replicated treatment plots 
                        connected with state-of-the-art ecophysiology and genomics labs and is available for 
                        undertaking new collaborative projects. FO3X consists of nine 5x5x2m blocks in which the 
                        concentrations of tropospheric ozone and other stress factors (e.g., drought, nitrogen, 
                        pathogens) can be controlled. The design is a split-plot experiment that provides the ability 
                        to assess the effects of ozone and other stressors, alone and in combination, 
                        on many plant attributes, including growth, root characteristics, gas exchanges, biogenic 
                        volatile organic compounds, nutrients as well as antioxidants. At present, plants are potted 
                        but can be planted in the ground if needed.
                        
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
