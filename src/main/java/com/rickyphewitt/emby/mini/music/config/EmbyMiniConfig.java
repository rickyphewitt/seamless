package com.rickyphewitt.emby.mini.music.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import com.rickyphewitt.emby.api.config.Config;
import com.rickyphewitt.emby.api.config.GsonConfig;

@Configuration
@ComponentScan({"com.rickyphewitt.emby.mini.music", "com.rickyphewitt.emby.api"})
@PropertySource({"classpath:application.properties"})
@Import({ThymeleafConfig.class, Config.class, GsonConfig.class})
public class EmbyMiniConfig {}