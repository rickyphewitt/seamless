package test.com.rickyphewitt.seamless.services;



import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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
import com.rickyphewitt.seamless.services.PlayService;
import com.rickyphewitt.seamless.services.SongService;
import com.rickyphewitt.seamless.services.publishers.PlayEventPublisher;

import test.com.rickyphewitt.seamless.services.config.TestConfig;
import test.com.rickyphewitt.seamless.services.helpers.SongTestHelper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class PlayServiceTests {

	// attributes
	@InjectMocks
	PlayService playService;
	
	@Mock
	SongService songService;
	
	@Mock
	PlayEventPublisher playEventPublisher;
	
	@Before
    public void setup() {
		MockitoAnnotations.initMocks(this.getClass());
    }

	
	@Test
	public void returnByteArrayOnPlayAlbumRequest() throws Exception {
		
		// data setup
		byte[] someBytes = new byte[100];
		String albumId = "randoAlbumId";
		List<Song> songs = setupSongs();
		
		Mockito.doNothing().when(playEventPublisher).setQueue(Mockito.anyListOf(Song.class));
		doNothing().when(songService).loadSongs(any(String.class));
		when(songService.getSongs()).thenReturn(songs);
		when(songService.playSong(any(String.class))).thenReturn(someBytes);
	
		
		// perform request
		byte[] bytes = playService.playAlbum(albumId, 1);
		
		// Assert
		Assert.assertEquals(someBytes.length, bytes.length);
		
	}
	
	private List<Song> setupSongs() {
		List<Song> songs = new ArrayList<Song>();
		for(int i = 0; i < 10; i++) {
			songs.add(SongTestHelper.createRandomSong());
		}
		return songs;
	}
	
}
