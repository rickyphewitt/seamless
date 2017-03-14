package com.mini.emby;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmbyMiniApplication {
	
	public static void main(String[] args) throws ClientProtocolException, IOException {
		SpringApplication.run(EmbyMiniApplication.class, args);
		
	}
}
