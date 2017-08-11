package com.rickyphewitt.seamless.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.rickyphewitt.seamless.data.Song;
import com.rickyphewitt.seamless.services.config.CachingConfig;

@Service
public class SongService extends MediaServiceBase<Song> {

	@Autowired
	Aggregator aggregatorService;
	
	private List<Song> songs;
	private HashMap<String, Song> songsById;
	private Map<Integer, Song> songsByTrack;
	private Map<String, Map<Integer, Song>> songsByAlbumByTrack;
	private String currentSongId;
	
	public SongService() {
		songsByAlbumByTrack = new HashMap<String, Map<Integer, Song>>();
		songsById = new HashMap<String, Song>();
		songsByTrack = new HashMap<Integer, Song>();
	}
	
	
	public void loadSongs(String albumId) throws InterruptedException, ExecutionException {
		
		List<Song> songs = aggregatorService.getSongsInAlbum(albumId);
		this.songs = this.consolidate(songs);
		this.songs.sort((Song o1, Song o2)->Integer.compare(o1.getTrackNumber(), o2.getTrackNumber()));
		addSongsToMap(albumId);
	}
	
	
	
	private void addSongsToMap(String albumId) {
		for(Song song: songs) {
			songsById.put(song.getMediaId(), song);
			songsByTrack.put(song.getTrackNumber(), song);
		}
		
		songsByAlbumByTrack.put(albumId, songsByTrack);
	}

	/**
	 * 
	 * Requests song from all sources and returns the 1st
	 * that completes
	 * 
	 * @param songId
	 * @return
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public byte[] playSong(String songId) throws InterruptedException, ExecutionException {
		return aggregatorService.playSong(songId);
	}
	
	
	// Getters/setters
	public List<Song> getSongs() {
		return songs;
	}

	public void setSongs(ArrayList<Song> songs) {
		this.songs = songs;
	}

	public HashMap<String, Song> getSongsMap() {
		return songsById;
	}

	public void setSongsMap(HashMap<String, Song> songsMap) {
		songsById = songsMap;
	}

	public String getCurrentSongId() {
		return currentSongId;
	}

	public void setCurrentSongId(String currentSongId) {
		this.currentSongId = currentSongId;
	}



	public HashMap<String, Song> getSongsById() {
		return songsById;
	}



	public void setSongsById(HashMap<String, Song> songsById) {
		this.songsById = songsById;
	}



	public Map<Integer, Song> getSongsByTrack() {
		return songsByTrack;
	}



	public void setSongsByTrack(Map<Integer, Song> songsByTrack) {
		this.songsByTrack = songsByTrack;
	}
}
