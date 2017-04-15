package test.com.mini.emby.services;



import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

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

import com.rickyphewitt.emby.api.data.Song;
import com.rickyphewitt.emby.api.data.SongSet;
import com.rickyphewitt.emby.mini.music.publishers.PlayEventPublisher;
import com.rickyphewitt.emby.mini.music.services.LoginService;
import com.rickyphewitt.emby.mini.music.services.PlayService;

import test.com.rickyphewitt.emby.mini.music.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class PlayServiceTests {

	// attributes
	@InjectMocks
	PlayService playService;
	
	@Mock
	LoginService loginService;
	
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
		SongSet songs = setupSongSet();
		
		Mockito.doNothing().when(playEventPublisher).setQueue(any(SongSet.class));
		when(loginService.getSongsFromAlbum(any(String.class))).thenReturn(songs);
		when(loginService.getSong(any(String.class))).thenReturn(someBytes);
	
		
		// perform request
		byte[] bytes = playService.playAlbum(albumId, 1);
		
		// Assert
		Assert.assertEquals(someBytes.length, bytes.length);
		
	}
	
	private SongSet setupSongSet() {
		String songId = "randosongId";
		SongSet songs = new SongSet();
		Song song = new Song();
		song.setId(songId);
		ArrayList<Song> songList = new ArrayList<Song>();
		songList.add(song);
		songs.setItems(songList);
		return songs;
	}
	
}
