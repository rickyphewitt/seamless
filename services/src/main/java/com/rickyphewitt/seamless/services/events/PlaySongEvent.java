package com.rickyphewitt.seamless.services.events;

import org.springframework.context.ApplicationEvent;

public class PlaySongEvent extends ApplicationEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	// Attributes
	private String songId;
	

	public PlaySongEvent(Object source, String songId) {
		super(source);
		this.songId = songId;
	}
	
	
	// getters/setters
	public String getSongId() {
		return songId;
	}

	public void setSongId(String songId) {
		this.songId = songId;
	}

}
