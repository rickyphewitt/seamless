package com.rickyphewitt.emby.mini.music;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.rickyphewitt.emby.api.data.SongSet;
import com.rickyphewitt.emby.mini.music.events.SetPlayQueueEvent;
import com.rickyphewitt.emby.mini.music.publishers.PlayEventPublisher;

@SpringBootApplication
public class EmbyMiniApplication {
	
	public static void main(String[] args) throws ClientProtocolException, IOException {
		SpringApplication.run(EmbyMiniApplication.class, args);		
	}
}
