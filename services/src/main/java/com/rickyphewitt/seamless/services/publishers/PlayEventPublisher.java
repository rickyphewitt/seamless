package com.rickyphewitt.seamless.services.publishers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.rickyphewitt.seamless.data.Song;
import com.rickyphewitt.seamless.services.events.PlaySongEvent;
import com.rickyphewitt.seamless.services.events.SetPlayQueueEvent;

@Component
public class PlayEventPublisher {

	private final ApplicationEventPublisher publisher;
	
	public PlayEventPublisher(ApplicationEventPublisher publisher){
		 this.publisher = publisher;
	}
	
    public void setQueue(List<Song> songs) {
        SetPlayQueueEvent setQueueEvent = new SetPlayQueueEvent(this, songs, 0);
        publisher.publishEvent(setQueueEvent);
    }
    
    public void setQueue(List<Song> songs, int playingItemNumber) {
        SetPlayQueueEvent setQueueEvent = new SetPlayQueueEvent(this, songs, playingItemNumber);
        publisher.publishEvent(setQueueEvent);
    }
    
    
    public void playSong(String songId) {
    	PlaySongEvent playSongEvent = new PlaySongEvent(this, songId);
    	publisher.publishEvent(playSongEvent);
    }
    
}
