package com.rickyphewitt.seamless.services;

import com.rickyphewitt.seamless.data.Song;
import com.rickyphewitt.seamless.data.exceptions.TrackDoesNotExistException;
import com.rickyphewitt.seamless.services.publishers.PlayEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

@Service
public class PlayService {

	@Autowired
	SongService songService;
	
	@Autowired
	PlayEventPublisher playEventPublisher;

	@Autowired
	AlbumService albumService;
	
	public byte[] playAlbum(String albumId, int startTrack) throws InterruptedException, ExecutionException, TrackDoesNotExistException {
		songService.loadSongs(albumId);
		int baseZeroTrackNumber = toBaseZero(startTrack);
		Map<Integer, Song> songsByTrack = songService.getSongsByTrack();
		playEventPublisher.setQueue(songService.getSongs(), baseZeroTrackNumber);

		if(!songsByTrack.containsKey(startTrack)) {
			String issue = "Unable to find track with number " +
					startTrack + " in album with id " + albumId;
			throw new TrackDoesNotExistException(issue);
		}

		String songId = songsByTrack.get(startTrack).getMediaId();
		return this.playSong(songId);
	}

	public byte[] shuffleAlbum(String albumId) throws ExecutionException, InterruptedException {
		songService.loadSongs(albumId);
		List<Song> songs = songService.getSongs();
		ShuffleService.shuffle(songs);
		playEventPublisher.setQueue(songs);
		return this.playSong(songs.get(0).getMediaId());
	}

	public byte[] shuffleArtist(String artistId) throws ExecutionException, InterruptedException {
		albumService.loadAlbums(artistId);
		List<String> albumIds = new ArrayList<String>(albumService.getAlbumsMap().keySet());
		songService.loadSongs(albumIds);
		List<Song> songs = songService.getSongs();
		ShuffleService.shuffle(songs);
		playEventPublisher.setQueue(songs);
		return this.playSong(songs.get(0).getMediaId());
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
