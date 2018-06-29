package com.rickyphewitt.seamless.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rickyphewitt.seamless.data.Album;

@Service
public class AlbumService extends MediaServiceBase<Album> {

	@Autowired
	ApiService loginService;
	
	@Autowired
	Aggregator aggregatorService;

	private static Logger logger = LogManager.getLogger();
	private List<Album> albums;
	private HashMap<String, Album> albumsMap;
	private String currentAlbumId;
	private boolean allAlbumsInitiated;
	private final String loadAllAlbums = "LOAD_ALL_ALBUMS";

	public AlbumService() {
		allAlbumsInitiated = false;
	}
	
	public void loadAlbums(String artistId) throws InterruptedException, ExecutionException {
		if(!allAlbumsInitiated) {
			List<Album> albums = new ArrayList<Album>();
			if(artistId.equals(loadAllAlbums)) {
				albums = aggregatorService.getAlbums();
			} else {
				albums = aggregatorService.getAlbumsByArtist(artistId);
			}

			this.albums = this.consolidate(albums);
			addAlbumsToMap(this.albums);
		} else {
			logger.info("All albums previously loaded, skipping specific artist load");
		}

	}

	public void loadAlbums() throws ExecutionException, InterruptedException {
		this.loadAlbums(loadAllAlbums);
	}
	
	private void addAlbumsToMap(List<Album> albums) {
		this.albumsMap = new HashMap<String, Album>();
		for(Album album: albums) {
			this.albumsMap.put(album.getMediaId(), album);
		}
	}


	private void setAlbumArtist(String artistId, List<Album> albums) {
		for(Album album: albums) {
			album.setArtistId(artistId);
		}
	}

	// Getters/Setters
	public List<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(ArrayList<Album> albums) {
		this.albums = albums;
	}

	public HashMap<String, Album> getAlbumsMap() {
		return albumsMap;
	}

	public void setAlbumsMap(HashMap<String, Album> albumsMap) {
		this.albumsMap = albumsMap;
	}

	public String getCurrentAlbumId() {
		return currentAlbumId;
	}
	
	public void setCurrentAlbumId(String currentAlbumId) {
		this.currentAlbumId = currentAlbumId;
	}


	
	
}
