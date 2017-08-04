package com.rickyphewitt.seamless.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rickyphewitt.seamless.data.Song;

@Service
public class SongService extends MediaServiceBase<Song> {

	@Autowired
	Aggregator aggregatorService;
	
	private List<Song> songs;
	private HashMap<String, Song> SongsMap;
	private String currentSongId;
	
	
	public void loadSongs(String albumId) throws InterruptedException, ExecutionException {
		List<Song> songs = aggregatorService.getSongsInAlbum(albumId);
		this.songs = this.consolidate(songs);
		this.songs.sort((Song o1, Song o2)->Integer.compare(o1.getTrackNumber(), o2.getTrackNumber()));
		addSongsToMap(this.songs);
	}
	
	private void addSongsToMap(List<Song> songs) {
		this.SongsMap = new HashMap<String, Song>();
		for(Song song: songs) {
			this.SongsMap.put(song.getMediaId(), song);
		}
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
		return SongsMap;
	}

	public void setSongsMap(HashMap<String, Song> songsMap) {
		SongsMap = songsMap;
	}

	public String getCurrentSongId() {
		return currentSongId;
	}

	public void setCurrentSongId(String currentSongId) {
		this.currentSongId = currentSongId;
	}
}
