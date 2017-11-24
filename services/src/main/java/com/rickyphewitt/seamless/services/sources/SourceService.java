package com.rickyphewitt.seamless.services.sources;

import java.util.List;

import com.rickyphewitt.seamless.data.Album;
import com.rickyphewitt.seamless.data.Artist;
import com.rickyphewitt.seamless.data.Song;
import com.rickyphewitt.seamless.data.exceptions.ConnectionException;

public interface SourceService {

	static final int defaultMaxAttempts = 5; 
	
	void login() throws ConnectionException;
	boolean isConnected();
	List<Artist> getArtists() throws Exception;
	List<Album> getAlbumsByArtist(String artistSourceId) throws Exception;
	List<Song> getSongsInAlbum(String albumSourceId) throws Exception;
	byte[] playSong(String songSourceId);
	String getPrimaryImage(String itemSourceId, String primaryImageId);
	byte[] getImage(String url);
}
