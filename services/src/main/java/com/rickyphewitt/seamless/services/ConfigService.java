package com.rickyphewitt.seamless.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rickyphewitt.seamless.data.Config;
import com.rickyphewitt.seamless.data.exceptions.ConfigException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class ConfigService {

	@Value("${config.file}")
	String configFile;

	@Value("${custom.config.directory}")
	String customConfigDir;

	private Config config;
	private static Logger logger = LogManager.getLogger();

	public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {	
		this.config = config;
	}

	/**
	 * Loads config from file
	 *
	 */
	//@ToDo make post construct
	public void loadConfigFromFile() {
		try {
			//this.customConfigFilePath = this.customConfigFile();
			this.makeConfigDirIfRequired();
			File f = this.readConfigFile();
			if(f != null) {
				Reader targetReader = null;
				try {
					targetReader = new FileReader(f);
					Gson gson = new GsonBuilder().create();
					this.config = gson.fromJson(targetReader, Config.class);
					targetReader.close();
					logger.info("Successfully loaded config! Source: " + config.getSource());
				} catch (IOException e) {
					logger.error("Unable to read config file!");
					this.setDefaultConfig();
				}
			}
		} catch(Exception e) {
			logger.error("Unable to load config file, exception: " + e.getMessage());
			this.setDefaultConfig();
		}
	}

	/**
	 * Updates the config file on the system with the supplied config
	 *
	 * @param config
	 * @throws ConfigException
	 */
	public void writeConfigfile(Config config) throws ConfigException{
		this.makeConfigDirIfRequired();
		PrintWriter writer = null;

		// set type as file
		config.setSource("file");
		config.setVersion(1);

		try {
			writer = new PrintWriter(this.customConfigFile(), "UTF-8");
		} catch (FileNotFoundException e) {
			String error = "Unable to find file at: " + this.customConfigFile();
			logger.error(error);
			ConfigException configException = new ConfigException(error, e);
			throw configException;
		} catch (UnsupportedEncodingException e) {
			String error = "Incorrect encoding for file at: " + this.customConfigFile();
			logger.error(error);
			ConfigException configException = new ConfigException(error, e);
			throw configException;
		}

		Gson gson = new GsonBuilder().create();
		writer.println(gson.toJson(config, Config.class));
		writer.close();
	}


	/**
	 * Sets default sane values
	 *
	 */
	private void setDefaultConfig() {
		logger.info("Falling back to hardcoded application defaults!");
		config = new Config();
		config.setSource("default");
		config.setPrefetchSongCount(5);
		config.setConfigDirectory(this.customConfigDir());

		
	}

	/**
	 * Loads custom or default config file
	 *
	 * @return file or null exception occurs
	 */
	private File readConfigFile() throws IOException {
		File f = null;
		f = new File(this.customConfigFile());
		if (!f.exists()) {
			logger.warn("Failed to find custom config file.");
			this.setDefaultConfig();
		}
		return f;
	}

	private void makeConfigDirIfRequired() {
		File directory = new File(System.getProperty("user.home") + "/" + this.customConfigDir);
		if (!directory.exists()) {
			directory.mkdir();
		}
	}

	/**
	 * Returns the absolute dir/custom.file path
	 *
	 * @return
	 */
	private String customConfigFile() {
		return this.customConfigDir() + this.configFile;
	}

	private String customConfigDir() {
		return System.getProperty("user.home") + "/" + this.customConfigDir;
	}

}
