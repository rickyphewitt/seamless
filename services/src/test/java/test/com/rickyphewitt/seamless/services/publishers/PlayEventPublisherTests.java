package test.com.rickyphewitt.seamless.services.publishers;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rickyphewitt.seamless.data.Song;
import com.rickyphewitt.seamless.services.PlayQueueService;
import com.rickyphewitt.seamless.services.observers.QueueObserver;
import com.rickyphewitt.seamless.services.publishers.PlayEventPublisher;

import test.com.rickyphewitt.seamless.services.config.TestConfig;
import test.com.rickyphewitt.seamless.services.helpers.SongTestHelper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class PlayEventPublisherTests {

	@Autowired
	PlayEventPublisher playEventPublisher;
	
	@Autowired
	QueueObserver queueObserver;
	
	@Autowired
	PlayQueueService playQueueService;
	
	@Test
	public void setPlayQueue() {
		
		List<Song> songs = setupSongs();
		
		playEventPublisher.setQueue(songs);
		
		Assert.assertEquals(songs.size(), playQueueService.getPlayQueue().size());
		
	}
	
	private List<Song> setupSongs() {
		List<Song> songs = new ArrayList<Song>();
		for(int i = 0; i < 10; i++) {
			songs.add(SongTestHelper.createRandomSong());
		}
		return songs;
	}
}
