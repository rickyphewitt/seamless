package com.rickyphewitt.seamless.services.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import com.rickyphewitt.emby.api.services.config.Config;
import com.rickyphewitt.emby.api.services.config.GsonConfig;

@Configuration
@ComponentScan({"com.rickyphewitt.services", "com.rickyphewitt.emby.api"})
@PropertySource({"classpath:application.properties"})
@Import({ThymeleafConfig.class, Config.class, GsonConfig.class})
public class SeamlessServicesConfig {}