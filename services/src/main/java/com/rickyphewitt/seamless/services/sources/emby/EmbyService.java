package com.rickyphewitt.seamless.services.sources.emby;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import com.rickyphewitt.emby.api.data.AuthenticationResult;
import com.rickyphewitt.emby.api.data.UserSet;
import com.rickyphewitt.emby.api.services.clients.ApiV1Client;
import com.rickyphewitt.seamless.data.Album;
import com.rickyphewitt.seamless.data.Artist;
import com.rickyphewitt.seamless.data.Song;
import com.rickyphewitt.seamless.data.exceptions.ConnectionException;
import com.rickyphewitt.seamless.data.sources.WebApiSource;
import com.rickyphewitt.seamless.services.sources.AsyncSourceService;
import com.rickyphewitt.seamless.services.sources.SourceService;
import com.rickyphewitt.seamless.services.sources.emby.deserializers.EmbyDeserializer;

@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "prototype")
public class EmbyService implements SourceService, AsyncSourceService {

	private static Logger logger = LogManager.getLogger();

	// Attributes
	private WebApiSource sourceSettings;
	private final static String defaultPassword = "da39a3ee5e6b4b0d3255bfef95601890afd80709";

	// Injections
	@Autowired
	ApiV1Client apiClient;
	
	public EmbyService() {
		System.out.println("new INSTANCE");
	}

	@Override
	@Retryable(maxAttempts = defaultMaxAttempts)
	public void login() throws ConnectionException {
		UserSet users = this.getPublicUsers();
		if(this.isSinglePublicUser(users)) {
			AuthenticationResult authResult = this.apiClient.authenticateByName(
					users.getItems().get(0).getName(),
					this.apiClient.getPassword());
			if(!isAuthed(authResult)) {
				String issue = "Failed to Login using name: " + this.sourceSettings.getName() +
						" and password " + this.sourceSettings.getPassword() + " using URL " +
						this.sourceSettings.getUrl() + ". Auth Result: " + authResult.toString();
				throw new ConnectionException(issue);
			}
		} else {
			//return users to display
			System.out.println("multiple users found, unable to login... IMPLEMENT");
		}
		
	}

	@Override
	@Retryable(maxAttempts = defaultMaxAttempts)
	public boolean isConnected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	@Retryable(maxAttempts = defaultMaxAttempts)
	public List<Artist> getArtists() {
		return EmbyDeserializer.deserialize(apiClient.getArtists());
	}

	@Override
	@Retryable(maxAttempts = defaultMaxAttempts)
	public List<Album> getAlbumsByArtist(String artistSourceId) {
		return EmbyDeserializer.deserialize(apiClient.getAlbumsByArtist(artistSourceId));
	}

	@Override
	@Retryable(maxAttempts = defaultMaxAttempts)
	public List<Album> getAlbums() {
		return EmbyDeserializer.deserialize(apiClient.getAlbums());
	}

	@Override
	@Retryable(maxAttempts = defaultMaxAttempts)
	public List<Song> getSongsInAlbum(String albumSourceId) {
		return EmbyDeserializer.deserialize(apiClient.getAlbumSongs(albumSourceId));
	}
	
	@Override
	@Retryable(maxAttempts = defaultMaxAttempts)
	public byte[] playSong(String songSourceId) {
		return apiClient.getSong(songSourceId);
	}

	@Override
	@Retryable(maxAttempts = defaultMaxAttempts)
	public String getPrimaryImage(String itemSourceId, String primaryImageId) {
		return apiClient.getPrimaryImageUrl(itemSourceId, primaryImageId);
	}

	@Override
	@Retryable(maxAttempts = defaultMaxAttempts)
	public byte[] getImage(String url) {
		return apiClient.getImage(url);
	}

	// private methods
	public UserSet getPublicUsers() {
		return apiClient.getPublicUsers();
	}

	private boolean isSinglePublicUser(UserSet users) {
		boolean singleUser = false;
		if(users.getItems().size() == 1) {
			singleUser = true;
		}
		
		return singleUser;
		
	}
	
	private boolean isAuthed(AuthenticationResult authResult) {
		boolean isAuthed = false;
		if(authResult.getAccessToken() != "" ){
				isAuthed = true;
		}
		
		return isAuthed;
	}
	

	
	// Async overrides
	@Override
	public CompletableFuture<List<Artist>> getAsyncArtists() {
		List<Artist> artists = this.getArtists();
		return CompletableFuture.completedFuture(artists);
	}

	@Override
	public CompletableFuture<List<Album>> getAsyncAlbumsByArtist(String artistSourceId) {
		List<Album> albums = this.getAlbumsByArtist(artistSourceId);
		return CompletableFuture.completedFuture(albums);
	}

	@Override
	public CompletableFuture<List<Album>> getAsyncAlbums() {
		List<Album> albums = this.getAlbums();
		return CompletableFuture.completedFuture(albums);
	}

	@Override
	public CompletableFuture<List<Song>> getAsyncSongsInAlbum(String albumSourceId) {
		List<Song> songs = this.getSongsInAlbum(albumSourceId);
		return CompletableFuture.completedFuture(songs);
	}

	@Override
	public CompletableFuture<byte[]> playAsyncSong(String songSourceId) {
		return CompletableFuture.completedFuture(this.playSong(songSourceId));
	}

	@Override
	public CompletableFuture<String> getAsyncPrimaryImage(String itemSourceId, String primaryImageId) {
		return CompletableFuture.completedFuture(this.getPrimaryImage(itemSourceId, primaryImageId));
	}

	@Override
	public CompletableFuture<byte[]> getAsyncImage(String url) {
		return CompletableFuture.completedFuture(this.getImage(url));
	}


	// Getters/Setters
	public WebApiSource getSourceSettings() {
		return sourceSettings;
	}

	public void setSourceSettings(WebApiSource sourceSettings) {
		this.sourceSettings = sourceSettings;
		if(this.sourceSettings.getPassword() == null) {
			this.sourceSettings.setPassword(defaultPassword);
		}
		this.apiClient.setEmbyUrl(this.sourceSettings.getUrl());
		this.apiClient.setUsername(this.sourceSettings.getUsername());
		this.apiClient.setPassword(this.sourceSettings.getPassword());
	}


}
