package com.rickyphewitt.seamless.services;

import com.rickyphewitt.seamless.data.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class ArtistService extends MediaServiceBase<Artist>{
	private static Logger logger = LogManager.getLogger();

	@Autowired
	private Aggregator aggregatorService;
	
	private List<Artist> artists;
	private HashMap<String, Artist> artistsMap;
	private String currentArtistId;

	/**
	 * Loads all artists from media sources using aggregator
	 * 
	 * @throws InterruptedException
	 * @throws ExecutionException
	 * 
	 * @see Aggregator
	 */
	public void loadArtists() throws InterruptedException, ExecutionException {
		logger.info("Requesting artists from services");
		this.artists = aggregatorService.getArtists();
		this.artists = this.consolidate(this.artists);
		this.artists.sort((Artist o1, Artist o2)->o1.getName().compareTo(o2.getName()));
		logger.info("Retrieved " + this.artists.size() + " from services");
		addArtistsToMap(artists);
	}
	
	/**
	 * Creates a hash map of MediaId -> Artist 
	 * 
	 * @param artists
	 */
	private void addArtistsToMap(List<Artist> artists) {
		this.artistsMap = new HashMap<String, Artist>();
		for(Artist artist: artists) {
			this.artistsMap.put(artist.getMediaId(), artist);
		}
	}
	
	// Getters/Setters
	public List<Artist> getArtists() {
		return artists;
	}

	public void setArtists(ArrayList<Artist> artists) {
		this.artists = (ArrayList<Artist>) artists;
	}

	public HashMap<String, Artist> getArtistsMap() {
		return artistsMap;
	}

	public void setArtistsMap(HashMap<String, Artist> artistsMap) {
		this.artistsMap = artistsMap;
	}

	public String getCurrentArtistId() {
		return currentArtistId;
	}

	public void setCurrentArtistId(String currentArtistId) {
		this.currentArtistId = currentArtistId;
	}
	
	
}
