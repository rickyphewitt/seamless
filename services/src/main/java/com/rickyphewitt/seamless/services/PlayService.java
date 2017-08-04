package com.rickyphewitt.seamless.services;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rickyphewitt.seamless.data.Song;
import com.rickyphewitt.seamless.services.publishers.PlayEventPublisher;

@Service
public class PlayService {

	@Autowired
	SongService songService;
	
	@Autowired
	PlayEventPublisher playEventPublisher;
	
	public byte[] playAlbum(String albumId, int startTrack) throws InterruptedException, ExecutionException {
		songService.loadSongs(albumId);
		int baseZeroTrackNumber = toBaseZero(startTrack);
		List<Song> songs = songService.getSongs();
		playEventPublisher.setQueue(songs, baseZeroTrackNumber);
		String songId = songs.get(baseZeroTrackNumber).getMediaId();
		return songService.playSong(songId);
	}
	
	public byte[] playQueueSong(String songId) throws InterruptedException, ExecutionException {
		return songService.playSong(songId);
	}
	
	public byte[] playSong(String songId) throws InterruptedException, ExecutionException {
		return songService.playSong(songId);
		
	}
	
	private int toBaseZero(int oneBaseInt) {
		return oneBaseInt = oneBaseInt > 0 ? oneBaseInt -1 : oneBaseInt;
	}
	
}
