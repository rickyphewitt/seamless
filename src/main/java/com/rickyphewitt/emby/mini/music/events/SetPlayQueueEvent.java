package com.rickyphewitt.emby.mini.music.events;

import com.rickyphewitt.emby.api.data.SongSet;

public class SetPlayQueueEvent extends PlayQueueEventBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SetPlayQueueEvent(Object source, SongSet songs) {
		super(source, songs);
	}


}
