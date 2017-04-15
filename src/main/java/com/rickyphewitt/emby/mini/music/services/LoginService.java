package com.rickyphewitt.emby.mini.music.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import com.rickyphewitt.emby.api.client.ApiV1Client;
import com.rickyphewitt.emby.api.data.AlbumSet;
import com.rickyphewitt.emby.api.data.ArtistSet;
import com.rickyphewitt.emby.api.data.SongSet;

@Service
public class LoginService {

	@Autowired
	ApiV1Client apiClient;
	
	public LoginService() {}
	
	@Retryable(maxAttempts = 5)
	public void login() {
		apiClient.authenticateByName();
	}
	
	@Retryable(maxAttempts = 5)
	public ArtistSet getArtists() {
		return apiClient.getArtists();
	}
	@Retryable(maxAttempts = 5)
	public AlbumSet getAlbumsByArtist(String artistId) {
		return apiClient.getAlbumsByArtist(artistId);
	}
	@Retryable(maxAttempts = 5)
	public SongSet getSongsFromAlbum(String albumId) {
		return apiClient.getAlbumSongs(albumId);
		
	}
	
	public byte[] getSong(String songId) {
		return apiClient.getSong(songId);
		
	}
	
}

