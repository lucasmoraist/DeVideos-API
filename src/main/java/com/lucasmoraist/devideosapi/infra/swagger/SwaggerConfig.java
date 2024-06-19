package com.lucasmoraist.devideosapi.infra.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI documentation(){
        return new OpenAPI()
                .info(new Info()
                        .title("Devideos API")
                        .description("API Dev Videos - Criada durante challenge proposto pela escola de programação Alura")
                        .summary("Está API serve para o gerenciamento de vídeos")
                        .version("V1")
                        .contact(new Contact()
                                .name("Lucas de Morais Nascimento Taguchi")
                                .url("https://lucasmoraist.github.io/Portfolio/")
                                .email("luksmnt1101@gmail.com")
                        )
                        .license(new License()
                                .name("MIT LICENSE")
                                .identifier("MIT")
                                .url("https://github.com/lucasmoraist/DeVideos-API/blob/main/LICENSE")
                        )
                );
    }
}
