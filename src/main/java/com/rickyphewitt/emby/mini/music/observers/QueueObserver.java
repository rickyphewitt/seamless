package com.rickyphewitt.emby.mini.music.observers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.rickyphewitt.emby.mini.music.events.SetPlayQueueEvent;
import com.rickyphewitt.emby.mini.music.services.PlayQueueService;

@Component
public class QueueObserver implements ApplicationListener<SetPlayQueueEvent>{

	@Autowired
	PlayQueueService playQueueService;
	
	
	@Override
	public void onApplicationEvent(SetPlayQueueEvent event) {
		playQueueService.setPlayQueue(event.getSongs());		
	}

}
