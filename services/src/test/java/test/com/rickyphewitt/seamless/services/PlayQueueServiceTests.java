package test.com.rickyphewitt.seamless.services;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rickyphewitt.emby.api.data.Song;
import com.rickyphewitt.emby.api.data.SongSet;
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
		SongSet songs = songSetup();
		playQueueService.setPlayQueue(songs);
		
		playQueueService.setPlayQueue(songs);
		Assert.assertTrue(playQueueService.getIndexes().size() == songs.getItems().size());
		Assert.assertTrue(playQueueService.getCurrentIndex() == 0);
	}
	
	
	@Test
	public void nextSong() {
	
		// Data setup
		SongSet songs = songSetup();
		playQueueService.setPlayQueue(songs);
		
		// Next
		playQueueService.next();
		Assert.assertTrue(playQueueService.getCurrentIndex() == 1);
		
		// Next again forcing loop
		playQueueService.next();
		Assert.assertTrue(playQueueService.getCurrentIndex() == 0);
	}

	@Test
	public void prevSong() {

		// Data setup
		SongSet songs = songSetup();
		playQueueService.setPlayQueue(songs);
		
		// prev forcing loop
		playQueueService.prev();
		Assert.assertTrue(playQueueService.getCurrentIndex() == 1);
		
		// prev 
		playQueueService.next();
		Assert.assertTrue(playQueueService.getCurrentIndex() == 0);
	}
	
	
	private SongSet songSetup() {
		Song firstSong = SongTestHelper.createSong("1", "awesomeSong");
		Song SecondSong = SongTestHelper.createSong("2", "awesomeSong2");
		
		
		SongSet songs = new SongSet();
		List<Song> items = new ArrayList<Song>();
		items.add(firstSong);
		items.add(SecondSong);
		songs.setItems(items);
		
		return songs;
	}

}
