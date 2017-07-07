package com.rickyphewitt.seamless.web.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan({"com.rickyphewitt.seamless.web"})
@PropertySource({"classpath:static"})
@Import({ThymeleafConfig.class})
public class SeamlessWebConfig {}
