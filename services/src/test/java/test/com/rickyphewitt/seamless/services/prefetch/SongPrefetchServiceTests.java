package test.com.rickyphewitt.seamless.services.prefetch;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rickyphewitt.seamless.data.Config;
import com.rickyphewitt.seamless.data.Song;
import com.rickyphewitt.seamless.services.ConfigService;
import com.rickyphewitt.seamless.services.PlayQueueService;
import com.rickyphewitt.seamless.services.SongService;
import com.rickyphewitt.seamless.services.prefetch.SongPrefetchService;

import test.com.rickyphewitt.seamless.services.config.TestConfig;
import test.com.rickyphewitt.seamless.services.helpers.SongTestHelper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class SongPrefetchServiceTests {

	// Attributes
	private static int prefetchSongCount = 4;
	
	@InjectMocks
	SongPrefetchService songPrefetchService;	
	
	@Mock
	ConfigService configService;
	
	@Mock
	SongService songService;
	
	@Mock
	PlayQueueService playQueueService;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		
		Config config = new Config();
		config.setPrefetchSongCount(prefetchSongCount);
		when(configService.getConfig()).thenReturn(config);
	}
	
	
	@Test
	public void prefetchNextNSongs_happyPath() throws InterruptedException, ExecutionException {
		// Data setup
		List<Song> songsInAlbum = SongTestHelper.createRandomSongsInAlbum(10, null);
		Map<Integer, Song> songsInAlbumByTrack = SongTestHelper.songsByTrackNumber(songsInAlbum);
		int currentlyPlayingIndex = 1;
		Song currentSong = songsInAlbumByTrack.get(currentlyPlayingIndex);
		String playSongId = currentSong.getMediaId();
		
		// Mocks
		when(playQueueService.getPlayQueue()).thenReturn(songsInAlbumByTrack);
		when(playQueueService.getCurrentIndex()).thenReturn(currentlyPlayingIndex);
		
		// playSong
		songPrefetchService.prefetchSongs();
		
		// verify
		verify(songService, times(prefetchSongCount)).playSong(any(String.class));
		for(int i = currentlyPlayingIndex; i < songsInAlbum.size(); i++) {
			if(i <= prefetchSongCount) {
				verify(songService, times(1)).playSong(songsInAlbumByTrack.get(i).getMediaId());
			} else {
				verify(songService, times(0)).playSong(songsInAlbumByTrack.get(i).getMediaId());
			}
			
		}
		
	}
	
	@Test
	public void prefetchNextNSongs_reachedEndOfQueue() throws InterruptedException, ExecutionException {
		// Data setup
		List<Song> songsInAlbum = SongTestHelper.createRandomSongsInAlbum(10, null);
		Map<Integer, Song> songsInAlbumByTrack = SongTestHelper.songsByTrackNumber(songsInAlbum);
		int currentlyPlayingIndex = 8;
		Song currentSong = songsInAlbumByTrack.get(currentlyPlayingIndex);
		
		// Mocks
		when(playQueueService.getPlayQueue()).thenReturn(songsInAlbumByTrack);
		when(playQueueService.getCurrentIndex()).thenReturn(currentlyPlayingIndex);
		when(playQueueService.isOutOfBounds(11)).thenReturn(true);
		
		// playSong
		songPrefetchService.prefetchSongs();
		
		// verify
		verify(songService, times(prefetchSongCount - 1)).playSong(any(String.class));
		for(int i = currentlyPlayingIndex; i < songsInAlbum.size(); i++) {
			if(i < (prefetchSongCount + currentlyPlayingIndex)) {
				verify(songService, times(1)).playSong(songsInAlbumByTrack.get(i).getMediaId());
			} else {
				verify(songService, times(0)).playSong(songsInAlbumByTrack.get(i).getMediaId());
			}
			
		}
		
	}
	
	
	
	
}
