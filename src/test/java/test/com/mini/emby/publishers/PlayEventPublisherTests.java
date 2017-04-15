package test.com.mini.emby.publishers;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rickyphewitt.emby.api.data.Song;
import com.rickyphewitt.emby.api.data.SongSet;
import com.rickyphewitt.emby.mini.music.observers.QueueObserver;
import com.rickyphewitt.emby.mini.music.publishers.PlayEventPublisher;
import com.rickyphewitt.emby.mini.music.services.PlayQueueService;

import test.com.rickyphewitt.emby.mini.music.TestConfig;

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
		
		SongSet songs = setupSongSet();
		
		playEventPublisher.setQueue(songs);
		
		Assert.assertEquals(songs.getItems().size(), playQueueService.getPlayQueue().size());
		
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
