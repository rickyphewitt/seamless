package com.rickyphewitt.emby.mini.music.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rickyphewitt.emby.api.data.SongSet;
import com.rickyphewitt.emby.mini.music.publishers.PlayEventPublisher;

@Service
public class PlayService {

	@Autowired
	ApiService loginService;
	
	@Autowired
	PlayEventPublisher playEventPublisher;
	
	public byte[] playAlbum(String albumId, int startTrack) {
		SongSet songs = loginService.getSongsFromAlbum(albumId);
		int baseZeroTrackNumber = toBaseZero(startTrack);
		
		// publish play event
		playEventPublisher.setQueue(songs, baseZeroTrackNumber);
		
		return playSong(songs.getItems().get(baseZeroTrackNumber).getId());
	}
	
	public byte[] playQueueSong(String songId) {
		return loginService.getSong(songId);
	}
	
	public byte[] playSong(String songId) {
		return loginService.getSong(songId);
		
	}
	
	private int toBaseZero(int oneBaseInt) {
		return oneBaseInt = oneBaseInt > 0 ? oneBaseInt -1 : oneBaseInt;
	}
	
}
