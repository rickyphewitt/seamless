package com.rickyphewitt.emby.mini.music.events;

import org.springframework.context.ApplicationEvent;

import com.rickyphewitt.emby.api.data.SongSet;

public abstract class PlayQueueEventBase extends ApplicationEvent {
	
	private SongSet songs;
	private int playingItemNumber;
	
	public PlayQueueEventBase(Object source, SongSet songs, int playingItemNumber) {
		super(source);
		this.songs = songs;
		this.playingItemNumber = playingItemNumber;
	}
	
	public SongSet getSongs() {
		return songs;
	}

	public void setSongs(SongSet songs) {
		this.songs = songs;
	}

	public int getPlayingItemNumber() {
		return playingItemNumber;
	}

	public void setPlayingItemNumber(int playingItemNumber) {
		this.playingItemNumber = playingItemNumber;
	}

	
}
