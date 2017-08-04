package com.rickyphewitt.seamless.services.events;

import java.util.List;

import org.springframework.context.ApplicationEvent;

import com.rickyphewitt.seamless.data.Song;

public abstract class PlayQueueEventBase extends ApplicationEvent {
	
	private List<Song> songs;
	private int playingItemNumber;
	
	public PlayQueueEventBase(Object source, List<Song> songs, int playingItemNumber) {
		super(source);
		this.songs = songs;
		this.playingItemNumber = playingItemNumber;
	}
	
	public List<Song> getSongs() {
		return songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

	public int getPlayingItemNumber() {
		return playingItemNumber;
	}

	public void setPlayingItemNumber(int playingItemNumber) {
		this.playingItemNumber = playingItemNumber;
	}

	
}
