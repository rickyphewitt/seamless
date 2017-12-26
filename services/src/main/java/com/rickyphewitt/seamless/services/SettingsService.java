package com.rickyphewitt.seamless.services;

import com.rickyphewitt.seamless.data.Config;
import com.rickyphewitt.seamless.data.SimpleResponse;
import com.rickyphewitt.seamless.data.exceptions.ConfigException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SettingsService {

	@Autowired
	ConfigService configService;


	public Config getConfig() {
		configService.loadConfigFromFile();
		return configService.getConfig();
	}

	public void writeConfig(Config c) throws ConfigException {
		configService.writeConfigfile(c);
	}

}
