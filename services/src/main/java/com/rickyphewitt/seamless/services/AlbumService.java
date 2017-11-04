package com.rickyphewitt.seamless.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rickyphewitt.seamless.data.Album;

@Service
public class AlbumService extends MediaServiceBase<Album> {

	@Autowired
	ApiService loginService;
	
	@Autowired
	Aggregator aggregatorService;
	
	private List<Album> albums;
	private HashMap<String, Album> albumsMap;
	private String currentAlbumId;
	
	public void loadAlbums(String artistId) throws InterruptedException, ExecutionException {
		List<Album> albums = aggregatorService.getAlbumsByArtist(artistId);
		this.albums = this.consolidate(albums);
		
		addAlbumsToMap(this.albums);
	}
	
	private void addAlbumsToMap(List<Album> albums) {
		this.albumsMap = new HashMap<String, Album>();
		for(Album album: albums) {
			this.albumsMap.put(album.getMediaId(), album);
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
