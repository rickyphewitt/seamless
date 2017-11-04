package com.rickyphewitt.seamless.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.rickyphewitt.seamless.data.Config;
import com.rickyphewitt.seamless.data.enums.IdSource;
import com.rickyphewitt.seamless.data.sources.WebApiSource;

@Service
public class SourceConfigService {

	// Attributes
	private HashMap<IdSource, List<WebApiSource>> webSources;	
	
	public SourceConfigService() {
		this.readConfig();
	}
	
	// Private methods
	private void readConfig() {
		//@ToDo: read user config file
		WebApiSource embySource = new WebApiSource("Emby1", IdSource.EMBY);
		embySource.setUsername("username1");
		embySource.setPassword("da39a3ee5e6b4b0d3255bfef95601890afd80709");
		embySource.setUrl("http://emby:8096");
		this.addWebSources(IdSource.EMBY, embySource);
		
		WebApiSource embySource2 = new WebApiSource("Emby2", IdSource.EMBY);
		embySource2.setUsername("username2");
		embySource2.setPassword("da39a3ee5e6b4b0d3255bfef95601890afd80709");
		embySource2.setUrl("http://emby:8096");
		this.addWebSources(IdSource.EMBY, embySource2);
		
		
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
	
	
}
