package com.rickyphewitt.seamless.services.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;

@Configuration
public class ThymeleafConfig {

	@Bean
	public SpringResourceTemplateResolver templateResolver() {
		
		SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
		resolver.setSuffix(".html");
		resolver.setTemplateMode("HTML5");
		
		return resolver;
		
	}
	
}
