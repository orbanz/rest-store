package com.orbanz.store;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "REST Store API",
                "RESTful webservice for a simple store app." +
                        "## How to Run \n" +
                        "\n" +
                        "This application is Spring Boot application which has Tomcat 8 embedded. No Tomcat or JBoss installation is necessary.\n" +
                        "\n" +
                        "* Clone this repository \n" +
                        "* Make sure you are using JDK 1.8 and Maven 3.x\n" +
                        "* You can build the project and run the tests by running ```mvn clean package```\n" +
                        "* Once successfully built, you can run the service the following way:\n" +
                        "```\n" +
                        "        mvn spring-boot:run\"\n" +
                        "```",
                "1.0",
                "urn:tos",
                new Contact("Zoltan Orban", "", "zoltan.orban@gmail.com"),
                "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", Collections.emptyList());
    }
}