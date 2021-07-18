package com.reece.addressbook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Class to create a swagger display for the exposed API's
 * 
 * @author Sarabjeet
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig 
{
	@Bean
	public Docket productApi() 
	{
		return new Docket(DocumentationType.SWAGGER_2)
				.select().apis(RequestHandlerSelectors.basePackage("com.reece.addressbook.controller"))
				.build();
	}
}
