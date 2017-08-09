package test.com.rickyphewitt.seamless.services;


import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rickyphewitt.seamless.data.Song;
import com.rickyphewitt.seamless.data.exceptions.ConnectionException;
import com.rickyphewitt.seamless.services.Aggregator;
import com.rickyphewitt.seamless.services.SongService;

import test.com.rickyphewitt.seamless.services.config.TestConfig;
import test.com.rickyphewitt.seamless.services.helpers.SongTestHelper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class SongServiceTests {
	
	@InjectMocks
	SongService songService;
	
	@Mock
	Aggregator aggregatorService;

	@Test
	public void happyPath_loadSongs() throws ConnectionException, InterruptedException, ExecutionException {
		
		// data setup
		String albumId = org.apache.commons.lang3.RandomStringUtils.randomAlphabetic(10);
		
		List<Song> songsInAlbum = SongTestHelper.createRandomSongsInAlbum(10, null);
		List<Song> doubleSongsForAlbum = songsInAlbum.stream().collect(Collectors.toList());
		songsInAlbum.addAll(doubleSongsForAlbum);
		Map<Integer, Song> songsByTrack = SongTestHelper.songsByTrackNumber(songsInAlbum);

		// mocks
		when(aggregatorService.getSongsInAlbum(albumId)).thenReturn(songsInAlbum);
		
		// run
		songService.loadSongs(albumId);
		
		// verify
		Assert.assertTrue(songService.getSongs().size() == songsInAlbum.size()/2);
		for(Song song: songsInAlbum) {
			SongTestHelper.assertEqual(song, songService.getSongsById().get(song.getMediaId()));
		}
		

	}
}

