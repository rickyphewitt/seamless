package com.rickyphewitt.emby.mini.music.services;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rickyphewitt.emby.api.data.Album;
import com.rickyphewitt.emby.api.data.AlbumSet;

@Service
public class AlbumService {

	@Autowired
	ApiService loginService;
	
	private HashMap<String, Album> albums;
	private AlbumSet albumSet;
	private String currentAlbumId;
	
	public void loadAlbums(String artistId) {
		this.albumSet = loginService.getAlbumsByArtist(artistId);
		addAlbumsToMap(albumSet);
	}
	
	private void addAlbumsToMap(AlbumSet albumSet) {
		this.albums = new HashMap<String, Album>();
		for(Album album: albumSet.getItems()) {
			this.albums.put(album.getId(), album);
		}
	}

	// Getters/Setters
	public HashMap<String, Album> getAlbums() {
		return albums;
	}

	public void setAlbums(HashMap<String, Album> albums) {
		this.albums = albums;
	}

	public AlbumSet getAlbumSet() {
		return albumSet;
	}

	public void setAlbumSet(AlbumSet albumSet) {
		this.albumSet = albumSet;
	}

	public String getCurrentAlbumId() {
		return currentAlbumId;
	}

	public void setCurrentAlbumId(String currentAlbumId) {
		this.currentAlbumId = currentAlbumId;
	}

	
	
}
