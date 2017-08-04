package com.rickyphewitt.seamless.services.events;

import java.util.List;

import com.rickyphewitt.seamless.data.Song;

public class SetPlayQueueEvent extends PlayQueueEventBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SetPlayQueueEvent(Object source, List<Song> songs, int playingItemNumber) {
		super(source, songs, playingItemNumber);
	}


}
