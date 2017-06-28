package com.rickyphewitt.seamless.services.events;

import com.rickyphewitt.emby.api.data.SongSet;

public class SetPlayQueueEvent extends PlayQueueEventBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SetPlayQueueEvent(Object source, SongSet songs, int playingItemNumber) {
		super(source, songs, playingItemNumber);
	}


}
