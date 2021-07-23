package com.erik5594.contato.api.openapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author erik_
 * Data Criacao: 21/07/2021 - 21:57
 */

@Configuration
@EnableSwagger2
public class SpringfoxConfig implements WebMvcConfigurer {

    @Bean
    public Docket apiDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.erik5594.contato.api"))
                .build()
                .apiInfo(apiInfo())
                .tags(new Tag("Pessoas", "Gerenciar cadastro de pessoas"));
    }

    public ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("API - Cadastro de pessoas")
                .description("API REST - Para realizar cadastro de pessoas e seus contatos")
                .version("1")
                .contact(new Contact("Erik Queiroz", "https://www.linkedin.com/in/erik-queiroz-de-oliveira-23a6a3100/", "erik.derick74@gmail.com"))
                .build();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/swagger-ui/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/")
                .resourceChain(false);
    }
}
