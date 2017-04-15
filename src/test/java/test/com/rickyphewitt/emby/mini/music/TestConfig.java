package test.com.rickyphewitt.emby.mini.music;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.web.WebAppConfiguration;

@Configuration
@ComponentScan({"com.rickyphewitt.emby.mini.music", "com.rickyphewitt.emby.api"})
@PropertySource({"classpath:application.properties"})
public class TestConfig {}
