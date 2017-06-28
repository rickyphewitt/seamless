package com.rickyphewitt.seamless.services.observers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.rickyphewitt.seamless.services.PlayQueueService;
import com.rickyphewitt.seamless.services.events.SetPlayQueueEvent;

@Component
public class QueueObserver implements ApplicationListener<SetPlayQueueEvent>{

	@Autowired
	PlayQueueService playQueueService;
	
	
	@Override
	public void onApplicationEvent(SetPlayQueueEvent event) {
		playQueueService.setPlayQueue(event.getSongs(), event.getPlayingItemNumber());		
	}

}
