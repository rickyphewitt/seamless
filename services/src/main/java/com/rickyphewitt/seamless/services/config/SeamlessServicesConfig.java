package com.rickyphewitt.seamless.services.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import com.rickyphewitt.emby.api.services.config.Config;
import com.rickyphewitt.emby.api.services.config.GsonConfig;

@Configuration
@ComponentScan({"com.rickyphewitt.seamless.services", "com.rickyphewitt.emby.api"})
@PropertySource({"classpath:application.properties"})
@Import({Config.class,
	GsonConfig.class,
	ThreadPoolConfig.class})
public class SeamlessServicesConfig {}