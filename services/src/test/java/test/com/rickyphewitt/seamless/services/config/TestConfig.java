package test.com.rickyphewitt.seamless.services.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.web.WebAppConfiguration;

@Configuration
@ComponentScan({"com.rickyphewitt.seamless.services"})
@PropertySource({"classpath:application.properties"})
public class TestConfig {}
