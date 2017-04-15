package com.rickyphewitt.emby.mini.music.events;

import org.springframework.context.ApplicationEvent;

import com.rickyphewitt.emby.api.data.SongSet;

public abstract class PlayQueueEventBase extends ApplicationEvent {
	
	private SongSet songs;

	public PlayQueueEventBase(Object source, SongSet songs) {
		super(source);
		this.songs = songs;
	}
	
	public SongSet getSongs() {
		return songs;
	}

	public void setSongs(SongSet songs) {
		this.songs = songs;
	}

	
}
