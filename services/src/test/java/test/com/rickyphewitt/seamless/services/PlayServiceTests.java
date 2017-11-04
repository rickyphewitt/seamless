package test.com.rickyphewitt.seamless.services;



import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rickyphewitt.seamless.data.Song;
import com.rickyphewitt.seamless.data.exceptions.TrackDoesNotExistException;
import com.rickyphewitt.seamless.services.PlayService;
import com.rickyphewitt.seamless.services.SongService;
import com.rickyphewitt.seamless.services.publishers.PlayEventPublisher;

import test.com.rickyphewitt.seamless.services.config.TestConfig;
import test.com.rickyphewitt.seamless.services.helpers.SongTestHelper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class PlayServiceTests {

	// attributes
	private static byte[] someBytes = new byte[100];
	
	
	@InjectMocks
	PlayService playService;
	
	@Mock
	SongService songService;
	
	@Mock
	PlayEventPublisher playEventPublisher;
	
	@Before
    public void setup() throws InterruptedException, ExecutionException {
		MockitoAnnotations.initMocks(this.getClass());
		Mockito.doNothing().when(playEventPublisher).setQueue(Mockito.anyListOf(Song.class));
		doNothing().when(songService).loadSongs(any(String.class));
	}

	
	@Test
	public void returnByteArrayOnPlayAlbumRequest() throws Exception {
		
		// data setup
		String albumId = "randoAlbumId";
		List<Song> songsInAlbum = SongTestHelper.createRandomSongsInAlbum(10, null);
		Map<Integer, Song> songsByTrack = SongTestHelper.songsByTrackNumber(songsInAlbum);
		
		// mocks
		when(songService.getSongs()).thenReturn(songsInAlbum);
		when(songService.playSong(any(String.class))).thenReturn(someBytes);
		when(songService.getSongsByTrack()).thenReturn(songsByTrack);
		
		// perform request
		byte[] bytes = playService.playAlbum(albumId, 1);
		
		// Assert
		Assert.assertEquals(someBytes.length, bytes.length);
		
	}
	
	@Test
	public void playNthTrackInAlbum_trackNumbersAreSequential() throws InterruptedException, ExecutionException, TrackDoesNotExistException {
		
		// data setup
		int nthTrack = 4;
		
		List<Song> songsInAlbum = SongTestHelper.createRandomSongsInAlbum(10, null);
		Map<Integer, Song> songsByTrack = SongTestHelper.songsByTrackNumber(songsInAlbum);

		// mocks
		when(songService.getSongs()).thenReturn(songsInAlbum);
		when(songService.getSongsByTrack()).thenReturn(songsByTrack);
		when(songService.playSong(any(String.class))).thenReturn(someBytes);
		
		// call
		playService.playAlbum("fakeAlbumHere", nthTrack);
		
		// verify
		verify(songService, times(1)).playSong(songsByTrack.get(4).getMediaId());
		
	}
	
	@Test
	public void playNthTrackInAlbum_trackNumbersAreNotSequential() throws InterruptedException, ExecutionException, TrackDoesNotExistException {
		
		// data setup
		int nthTrack = 4;
		Set<Integer> trackstoExclude = new HashSet<Integer>();
		trackstoExclude.add(3);
		trackstoExclude.add(5);

		
		List<Song> songsInAlbum = SongTestHelper.createRandomSongsInAlbum(10, trackstoExclude);
		Map<Integer, Song> songsByTrack = SongTestHelper.songsByTrackNumber(songsInAlbum);

		// mocks
		when(songService.getSongs()).thenReturn(songsInAlbum);
		when(songService.getSongsByTrack()).thenReturn(songsByTrack);
		when(songService.playSong(any(String.class))).thenReturn(someBytes);
		
		// call
		playService.playAlbum("fakeAlbumHere", nthTrack);
		
		// verify
		verify(songService, times(1)).playSong(songsByTrack.get(4).getMediaId());
		
	}
	
	@Test(expected=TrackDoesNotExistException.class)
	public void playNthTrackInAlbum_trackDoesNotExist() throws InterruptedException, ExecutionException, TrackDoesNotExistException {
		
		// data setup
		int nthTrack = 3;
		Set<Integer> trackstoExclude = new HashSet<Integer>();
		trackstoExclude.add(3);
		trackstoExclude.add(5);

		
		List<Song> songsInAlbum = SongTestHelper.createRandomSongsInAlbum(10, trackstoExclude);
		Map<Integer, Song> songsByTrack = SongTestHelper.songsByTrackNumber(songsInAlbum);

		// mocks
		when(songService.getSongs()).thenReturn(songsInAlbum);
		when(songService.getSongsByTrack()).thenReturn(songsByTrack);
		when(songService.playSong(any(String.class))).thenReturn(someBytes);
		
		// call
		playService.playAlbum("fakeAlbumHere", nthTrack);
		
		// verify
		verify(songService, times(1)).playSong(songsByTrack.get(4).getMediaId());
		
	}

	@Test
	public void playShuffle() throws ExecutionException, InterruptedException {
		// data setup
		String albumId = "randoAlbumId";
		List<Song> songsInAlbum = SongTestHelper.createRandomSongsInAlbum(10, null);
		Map<Integer, Song> songsByTrack = SongTestHelper.songsByTrackNumber(songsInAlbum);

		// mocks
		when(songService.getSongs()).thenReturn(songsInAlbum);
		when(songService.playSong(any(String.class))).thenReturn(someBytes);
		when(songService.getSongsByTrack()).thenReturn(songsByTrack);

		// perform request
		byte[] bytes = playService.shuffleAlbum(albumId);
	}
	
}
