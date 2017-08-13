package com.rickyphewitt.seamless.services.observers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.rickyphewitt.seamless.services.PlayQueueService;
import com.rickyphewitt.seamless.services.events.SetPlayQueueEvent;

@Component
public class QueueObserver {

	@Autowired
	PlayQueueService playQueueService;
	
	
	@EventListener
	public void handleEvent(SetPlayQueueEvent event) {
		playQueueService.setPlayQueue(event.getSongs(), event.getPlayingItemNumber());		
	}

}
