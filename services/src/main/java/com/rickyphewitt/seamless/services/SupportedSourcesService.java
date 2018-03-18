package com.rickyphewitt.seamless.services;

import com.rickyphewitt.seamless.data.SupportedSource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class SupportedSourcesService {

	// Attributes
	List<SupportedSource> sources;


	public List<SupportedSource> getSources() {
		return sources;
	}

	public void setSources(List<SupportedSource> sources) {
		this.sources = sources;
	}

	@PostConstruct
	private void init() {
		// add emby as supported source
		this.sources = new ArrayList<>();

		for (int i = 0; i < 2; i++) {
			SupportedSource emby = new SupportedSource();
			emby.setName("Emby");
			emby.setDescription("Take your Media Anywhere");
			emby.setImage("https://emby.media/notificationicon.png");
			emby.setUrl("https://emby.media/");
			emby.setConfigName("emby");

			this.sources.add(emby);
		}

	}

}
