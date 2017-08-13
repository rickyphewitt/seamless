package com.rickyphewitt.seamless.services;

import org.springframework.stereotype.Service;

import com.rickyphewitt.seamless.data.Config;

@Service
public class ConfigService {

	private Config config;
	
	public ConfigService() {
		setDefaultConfig();
	}
	

	public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {	
		this.config = config;
	}
	

	private void setDefaultConfig() {
		config = new Config();
		config.setPrefetchSongCount(5);
		
	}
	
}
