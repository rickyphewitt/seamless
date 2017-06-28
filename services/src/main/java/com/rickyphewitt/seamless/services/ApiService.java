package com.rickyphewitt.seamless.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import com.rickyphewitt.emby.api.data.AlbumSet;
import com.rickyphewitt.emby.api.data.ArtistSet;
import com.rickyphewitt.emby.api.data.AuthenticationResult;
import com.rickyphewitt.emby.api.data.PublicServerInfo;
import com.rickyphewitt.emby.api.data.SongSet;
import com.rickyphewitt.emby.api.data.UserSet;
import com.rickyphewitt.emby.api.services.clients.ApiV1Client;

@Service
public class ApiService {

	@Value("${emby.url}")
	private String embyUrl;
	
	@Value("${emby.username}")
	private String username;

	@Value("${emby.password}")
	private String password;

	@Value("${emby.password.default}")
	private String passwordDefault;
	
	@Autowired
	ApiV1Client apiClient;
	
	public ApiService() {}
	
	public PublicServerInfo getPublicServerInfo(String embyUrl) {
		return apiClient.getPublicServerInfo(embyUrl);
	}
	
	@Retryable(maxAttempts = 5)
	public UserSet getPublicUsers() {
		return apiClient.getPublicUsers();
	}
	
	@Retryable(maxAttempts = 5)
	public void login() {
		apiClient.authenticateByName();
	}
	
	public AuthenticationResult login(String username) {
		return apiClient.authenticateByName(username);
	}
	
	public AuthenticationResult login(String username, String password) {
		return apiClient.authenticateByName(username, password);
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

	// getters/setters
	
	public String getEmbyUrl() {
		return embyUrl;
	}

	public void setEmbyUrl(String embyUrl) {
		this.embyUrl = embyUrl;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordDefault() {
		return passwordDefault;
	}

	public void setPasswordDefault(String passwordDefault) {
		this.passwordDefault = passwordDefault;
	}

	public ApiV1Client getApiClient() {
		return apiClient;
	}

	public void setApiClient(ApiV1Client apiClient) {
		this.apiClient = apiClient;
	}
	
}

