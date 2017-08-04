package test.com.rickyphewitt.seamless.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rickyphewitt.seamless.data.Song;
import com.rickyphewitt.seamless.services.PlayQueueService;

import test.com.rickyphewitt.seamless.services.config.TestConfig;
import test.com.rickyphewitt.seamless.services.helpers.SongTestHelper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class PlayQueueServiceTests {

	@Autowired
	PlayQueueService playQueueService;
	
	
	
	
	@Test
	public void initPlayQueueAndIndexes() {

		// Data setup
		List<Song> songs = setupSongs(2);
		playQueueService.setPlayQueue(songs);
		
		Assert.assertTrue(playQueueService.getIndexes().size() == songs.size());
		Assert.assertTrue(playQueueService.getCurrentIndex() == 0);
	}
	
	
	@Test
	public void nextSong_forceWrapping() {
	
		// Data setup
		List<Song> songs = setupSongs(2);
		playQueueService.setPlayQueue(songs);
		
		// Next
		playQueueService.next();
		Assert.assertTrue(playQueueService.getCurrentIndex() == 1);
		
		// Next again forcing loop
		playQueueService.next();
		Assert.assertTrue(playQueueService.getCurrentIndex() == 0);
	}

	@Test
	public void prevSong_forceWrapping() {

		// Data setup
		List<Song> songs = setupSongs(2);
		playQueueService.setPlayQueue(songs);
		
		// prev forcing loop
		playQueueService.prev();
		Assert.assertTrue(playQueueService.getCurrentIndex() == 1);
		
		// prev 
		playQueueService.next();
		Assert.assertTrue(playQueueService.getCurrentIndex() == 0);
	}
	
	
	@Test
	public void nextPrevSong_largerQueue() {
		List<Song> songs = setupSongs(10);
		Map<Integer, Song> songIndexes= new HashMap<Integer, Song>();
		for(int i = 0; i < songs.size(); i ++) {
			songIndexes.put(i, songs.get(i));
		}
		
		playQueueService.setPlayQueue(songs);
		
		// Next
		playQueueService.next();
		Assert.assertTrue(playQueueService.getCurrentIndex() == 1);
		
		playQueueService.next();
		Assert.assertTrue(playQueueService.getCurrentIndex() == 2);
		
		playQueueService.next();
		Assert.assertTrue(playQueueService.getCurrentIndex() == 3);
				
		// Prev
		playQueueService.prev();
		Assert.assertTrue(playQueueService.getCurrentIndex() == 2);
		
	}
	
	// helper methods
	private List<Song> setupSongs(int songsToCreate) {
		List<Song> songs = new ArrayList<Song>();
		for(int i = 0; i < songsToCreate; i++) {
			songs.add(SongTestHelper.createRandomSong());
		}
		return songs;
	}

}
