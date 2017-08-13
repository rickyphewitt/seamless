package com.rickyphewitt.seamless.services.observers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.rickyphewitt.seamless.services.events.PlaySongEvent;
import com.rickyphewitt.seamless.services.prefetch.SongPrefetchService;

@Component
public class PlaySongObserver {

	@Autowired
	SongPrefetchService songPrefetchService;
	
	@Async
	@EventListener
	void handleEvent(PlaySongEvent event) {
		songPrefetchService.prefetchSongs();
	}

}
