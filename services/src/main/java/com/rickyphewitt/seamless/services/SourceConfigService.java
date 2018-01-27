package com.rickyphewitt.seamless.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rickyphewitt.seamless.data.enums.IdSource;
import com.rickyphewitt.seamless.data.sources.WebApiSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

@Service
public class SourceConfigService {


	@Value("${source.config.directory}")
	String sourceConfigDirectory;

	// Attributes
	private HashMap<IdSource, List<WebApiSource>> webSources;
	private static Logger logger = LogManager.getLogger();

	// Private methods

	/**
	 * Loads all configs from source folder
	 *
	 */
	public void loadSources() {
		List<File> sources = new ArrayList<>();
		List<Path> fileNames = new ArrayList<>();

		this.makeConfigDirIfRequired();
		try (Stream<Path> paths = Files.walk(Paths.get(System.getProperty("user.home") + "/" + this.sourceConfigDirectory))) {
			paths
					.filter(Files::isRegularFile)
					.forEach(s -> fileNames.add(s.getFileName()));
		} catch(IOException e) {
			logger.error("Unable to load sources!" + e.getMessage());
		}

		logger.info("Found: " + fileNames.size() + " existing configuration files.");

		for (Path p: fileNames) {
			sources.add(new File(System.getProperty("user.home") + "/" + this.sourceConfigDirectory + "/" +p.toString()));
		}

		sortSources(sources);
	}

	
	// Getters/Setters
	public HashMap<IdSource, List<WebApiSource>> getWebSources() {
		return webSources;
	}

	public void setWebSources(HashMap<IdSource, List<WebApiSource>> webSources) {
		this.webSources = webSources;
	}
	
	public void addWebSources(IdSource source, WebApiSource config) {
		if(this.webSources == null) {
			this.webSources = new HashMap<IdSource, List<WebApiSource>>();
		}
		
		if(this.webSources.containsKey(source)) {
			List<WebApiSource> existingSources = this.webSources.get(source);
			existingSources.add(config);
			this.webSources.put(source, existingSources);
		} else {
			List<WebApiSource> newSource = new ArrayList<WebApiSource>();
			newSource.add(config);
			this.webSources.put(source, newSource);
		}
	}


	// private methods

	/**
	 * Helper method to make config directory if doesn't exist
	 * This would run in the 1st run case
	 */
	private void makeConfigDirIfRequired() {
		File directory = new File(System.getProperty("user.home") + "/" + this.sourceConfigDirectory);
		if (!directory.exists()) {
			directory.mkdir();
		}
	}

	/**
	 * Sorts sources into their IdSource
	 *
	 * @param sources
	 */
	private void sortSources(List<File> sources) {
		for(File file: sources) {
			if(file.getName().contains(IdSource.EMBY.toString())) {
				try {
					addToMap(IdSource.EMBY, file);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Adds sources to map
	 *
	 * @param idSource
	 * @param source
	 * @throws FileNotFoundException
	 */
	private void addToMap(IdSource idSource, File source) throws FileNotFoundException {

		if(this.webSources == null) {
			this.webSources = new HashMap<IdSource, List<WebApiSource>>();
		}
		if(this.webSources.containsKey(idSource)) {
			List<WebApiSource> existingSources = this.webSources.get(idSource);
			existingSources.add(readWebApiSourceFile(source));
			this.webSources.put(idSource, existingSources);
		} else {
			List<WebApiSource> sources = new ArrayList<>();
			sources.add(readWebApiSourceFile(source));
			this.webSources.put(idSource, sources);
		}

	}

	/**
	 * Reads web api souce from file into instance
	 *
	 * @TODO: create a global gson config
	 *
	 * @param source
	 * @return
	 * @throws FileNotFoundException
	 */
	private WebApiSource readWebApiSourceFile(File source) throws FileNotFoundException {
		Reader targetReader = new FileReader(source);
		Gson gson = new GsonBuilder().create();
		WebApiSource webApiSource = gson.fromJson(targetReader, WebApiSource.class);
		return webApiSource;
	}


}
