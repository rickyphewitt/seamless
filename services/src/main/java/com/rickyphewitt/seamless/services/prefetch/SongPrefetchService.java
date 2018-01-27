package com.rickyphewitt.seamless.services.prefetch;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rickyphewitt.seamless.data.Song;
import com.rickyphewitt.seamless.services.ConfigService;
import com.rickyphewitt.seamless.services.PlayQueueService;
import com.rickyphewitt.seamless.services.SongService;

@Service
public class SongPrefetchService {
	private static Logger logger = LogManager.getLogger();

	@Autowired
	ConfigService configService;
	
	@Autowired
	PlayQueueService playQueueService;
	
	@Autowired
	SongService songService;
	
	public void prefetchSongs() {
		if(configService.getConfig() == null) {
			//@ToDo: make a post construct on config service
			configService.loadConfigFromFile();
		}
		logger.info("Eagerly prefetching next " +
				configService.getConfig().getPrefetchSongCount() + " songs");
		
		Map<Integer, Song> playQueue = playQueueService.getPlayQueue();
		int currentlyPlayingItem = playQueueService.getCurrentIndex();
		int preFetchCount = configService.getConfig().getPrefetchSongCount();	
		
		
		for(int i = currentlyPlayingItem; i < (currentlyPlayingItem + preFetchCount); i++) {
			if(!playQueueService.isOutOfBounds(i)) {
				try {
					songService.playSong(playQueue.get(i).getMediaId());
					logger.info("Cached: " + playQueue.get(i).getMediaId() );
				} catch (InterruptedException | ExecutionException e) {
					logger.error("FAILED TO CACHE song with media id" + playQueue.get(i).getMediaId());
					e.printStackTrace();
				}
			} else {
				logger.info("Reached end of Qeueue, no songs to Cache");
			}
			
			
		}
	}
	
}
