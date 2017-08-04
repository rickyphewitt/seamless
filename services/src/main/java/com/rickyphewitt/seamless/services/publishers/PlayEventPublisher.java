package com.rickyphewitt.seamless.services.publishers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.rickyphewitt.seamless.data.Song;
import com.rickyphewitt.seamless.services.events.SetPlayQueueEvent;

@Component
public class PlayEventPublisher {

	@Autowired
    private ApplicationEventPublisher applicationEventPublisher;
 
    public void setQueue(List<Song> songs) {
        SetPlayQueueEvent setQueueEvent = new SetPlayQueueEvent(this, songs, 0);
        applicationEventPublisher.publishEvent(setQueueEvent);
    }
    
    public void setQueue(List<Song> songs, int playingItemNumber) {
        SetPlayQueueEvent setQueueEvent = new SetPlayQueueEvent(this, songs, playingItemNumber);
        applicationEventPublisher.publishEvent(setQueueEvent);
    }
    
}
