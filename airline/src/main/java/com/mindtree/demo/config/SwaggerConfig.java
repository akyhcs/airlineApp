package com.mindtree.demo.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@Configuration
public class SwaggerConfig {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build();
	}

//	private static final String SWAGGER_API_VERSION = "2.0";
//	private static final String title = "Sales Report";
//	private static final String description = "RESTful API for SalesReport";
//	private static final String LICENSE_TEXT = "License";
//
//	private ApiInfo apiInfo(){
//		return new ApiInfoBuilder().title(title).description(description).license(LICENSE_TEXT)
//				.version(SWAGGER_API_VERSION).build();
//	}


}
