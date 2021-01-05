package com.jsanin.takehome.converter.config;

import com.jsanin.takehome.converter.ConverterUrlMapping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket swaggerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.jsanin.takehome.converter"))
                .paths(PathSelectors.ant(ConverterUrlMapping.CONVERTER_BASE_PATH + "/**"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().termsOfServiceUrl("").title("Number conversion API")
                .description("Take home exercise for sonatype recruitment process. Backend project").license("").licenseUrl("")
                .contact(new Contact(
                        "Juan Sanin",
                        "https://www.linkedin.com/in/juan-sanin-949483197/",
                        "jsanin@gmail.com"))
                .build();
    }

}
