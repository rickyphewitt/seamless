package com.rickyphewitt.seamless.services;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rickyphewitt.emby.api.data.Artist;
import com.rickyphewitt.emby.api.data.ArtistSet;

@Service
public class ArtistService {

	@Autowired
	ApiService loginService;
	
	private HashMap<String, Artist> artists;
	private ArtistSet artistSet;
	private String currentArtistId;
	
	public void loadArtists() {
		this.artistSet = loginService.getArtists();
		addArtistsToMap(artistSet);
		
		
	}
	
	private void addArtistsToMap(ArtistSet artistSet) {
		this.artists = new HashMap<String, Artist>();
		for(Artist artist: artistSet.getItems()) {
			this.artists.put(artist.getId(), artist);
		}
	}

	// Getters/Setters
	public HashMap<String, Artist> getArtists() {
		return artists;
	}

	public void setArtists(HashMap<String, Artist> artists) {
		this.artists = artists;
	}

	public ArtistSet getArtistSet() {
		return artistSet;
	}

	public void setArtistSet(ArtistSet artistSet) {
		this.artistSet = artistSet;
	}

	public String getCurrentArtistId() {
		return currentArtistId;
	}

	public void setCurrentArtistId(String currentArtistId) {
		this.currentArtistId = currentArtistId;
	}
	
	
}
