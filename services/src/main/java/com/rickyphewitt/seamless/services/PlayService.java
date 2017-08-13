package com.rickyphewitt.seamless.services;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rickyphewitt.seamless.data.Song;
import com.rickyphewitt.seamless.data.exceptions.TrackDoesNotExistException;
import com.rickyphewitt.seamless.services.publishers.PlayEventPublisher;

@Service
public class PlayService {

	@Autowired
	SongService songService;
	
	@Autowired
	PlayEventPublisher playEventPublisher;
	
	public byte[] playAlbum(String albumId, int startTrack) throws InterruptedException, ExecutionException, TrackDoesNotExistException {
		songService.loadSongs(albumId);
		int baseZeroTrackNumber = toBaseZero(startTrack);
		Map<Integer, Song> songsByTrack = songService.getSongsByTrack();
		playEventPublisher.setQueue(songService.getSongs(), baseZeroTrackNumber);
		if(songsByTrack.containsKey(startTrack)) {
			
		} else {
			String issue = "Unable to find track with number " +
					startTrack + " in album with id " + albumId;
			throw new TrackDoesNotExistException(issue);
		}
		String songId = songsByTrack.get(startTrack).getMediaId();
		return this.playSong(songId);
	}
	
	public byte[] playQueueSong(String songId) throws InterruptedException, ExecutionException {
		return this.playSong(songId);
	}
	
	public byte[] playSong(String songId) throws InterruptedException, ExecutionException {
		playEventPublisher.playSong(songId);
		return songService.playSong(songId);
		
	}
	
	private int toBaseZero(int oneBaseInt) {
		return oneBaseInt = oneBaseInt > 0 ? oneBaseInt -1 : oneBaseInt;
	}
	
}
