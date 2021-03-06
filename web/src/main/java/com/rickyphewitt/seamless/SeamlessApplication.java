package com.rickyphewitt.seamless;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.rickyphewitt.seamless.web.config.SeamlessWebConfig;

@SpringBootApplication
@Import({SeamlessWebConfig.class})
public class SeamlessApplication {
	
	public static void main(String[] args) throws ClientProtocolException, IOException {
		SpringApplication.run(SeamlessApplication.class, args);		
	}
}
